package com.example.meatwaybackend.service.ad;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dao.ad.BeefRepository;
import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdSaveRequest;
import com.example.meatwaybackend.dto.ad.beef.BeefAdsRequest;
import com.example.meatwaybackend.handler.exception.InternalServerErrorException;
import com.example.meatwaybackend.handler.exception.auth.ForbiddenAccessException;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.AdMapper;
import com.example.meatwaybackend.model.ad.Beef;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeefService {
    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_SIZE = 10;
    private final static String DEFAULT_SORT = "id";

    private final BeefRepository beefRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;

    public ShortAdsResponse findAll(Integer page, Integer size, String sort, BeefAdsRequest request) {
        Specification<Beef> spec = getBeefSpecification(request);

        if (sort == null || sort.isBlank()) {
            sort = DEFAULT_SORT;
        }

        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(DEFAULT_PAGE),
                Optional.ofNullable(size).orElse(DEFAULT_SIZE),
                Sort.by(sort)
        );

        Page<Beef> pageResult = beefRepository.findAll(spec, pageable);

        return new ShortAdsResponse(
                adMapper.toListShortBeefAdResponse(pageResult.getContent()),
                pageResult.getTotalElements()
        );
    }


    private static Specification<Beef> getBeefSpecification(BeefAdsRequest request) {
        Specification<Beef> spec = Specification.where(null);

        if (request == null) {
            return spec;
        }

        if (request.isRetail() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("isRetail"), request.isRetail()));
        }
        if (request.medicalCertificate() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("medicalCertificate"), request.medicalCertificate()));
        }
        if (request.isFrozen() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("isFrozen"), request.isFrozen()));
        }
        if (request.priceFrom() != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), request.priceFrom()));
        }
        if (request.priceTo() != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), request.priceTo()));
        }
        if (request.weightFrom() != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("weight"), request.weightFrom()));
        }
        if (request.weightTo() != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("weight"), request.weightTo()));
        }
        if (request.quantityFrom() != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("quantity"), request.quantityFrom()));
        }
        if (request.quantityTo() != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("quantity"), request.quantityTo()));
        }
        if (request.monthsAgeFrom() != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("monthsAge"), request.monthsAgeFrom()));
        }
        if (request.monthsAgeTo() != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("monthsAge"), request.monthsAgeTo()));
        }
        if (request.dateBegin() != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), request.dateBegin()));
        }
        if (request.dateEnd() != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), request.dateEnd()));
        }
        return spec;
    }

    public BeefAdResponse findById(long id) {
        Beef beef = beefRepository.findById(id).orElseThrow(() -> new NotFoundException(Beef.class, id));

        return adMapper.toBeefAdResponse(beef);
    }

    public BeefAdResponse createBeefAd(BeefAdSaveRequest request, String email) {
        Beef newBeef = adMapper.toBeef(request);
        newBeef.setSellerUser(userRepository.findByEmail(email).orElseThrow(
                () -> new InternalServerErrorException("Несущестующий пользователь пытается создать объявление")
        ));
        Beef beef = beefRepository.save(newBeef);

        return adMapper.toBeefAdResponse(beef);
    }

    public BeefAdResponse patchById(long id, BeefAdSaveRequest request, String email) {
        Beef beef = beefRepository.findById(id).orElseThrow(() -> new NotFoundException(Beef.class, id));
        validateBeefAdOwner(email, beef);
        adMapper.updateBeefFromBeefAdSaveRequest(beef, request);
        beefRepository.save(beef);

        return adMapper.toBeefAdResponse(beef);
    }

    public void deleteById(long id, String email) {
        Beef beef = beefRepository.findById(id).orElseThrow(() -> new NotFoundException(Beef.class, id));
        validateBeefAdOwner(email, beef);
        beefRepository.deleteById(id);
    }

    private void validateBeefAdOwner(String email, Beef beef) {
        var sellerUser = beef.getSellerUser();

        if (!sellerUser.getEmail().equals(email)) {
            throw new ForbiddenAccessException(email);
        }
    }
}
