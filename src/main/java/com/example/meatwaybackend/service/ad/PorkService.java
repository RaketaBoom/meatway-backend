package com.example.meatwaybackend.service.ad;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dao.ad.PorkRepository;
import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdSaveRequest;
import com.example.meatwaybackend.dto.ad.pork.PorkAdsRequest;
import com.example.meatwaybackend.handler.exception.InternalServerErrorException;
import com.example.meatwaybackend.handler.exception.auth.ForbiddenAccessException;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.AdMapper;
import com.example.meatwaybackend.model.ad.Pork;
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
public class PorkService {
    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_SIZE = 10;
    private final static String DEFAULT_SORT = "id";

    private final PorkRepository porkRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;

    public ShortAdsResponse findAll(Integer page, Integer size, String sort, PorkAdsRequest request) {
        Specification<Pork> spec = getPorkSpecification(request);
        if (sort == null || sort.isBlank()) {
            sort = DEFAULT_SORT;
        }

        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(DEFAULT_PAGE),
                Optional.ofNullable(size).orElse(DEFAULT_SIZE),
                Sort.by(sort)
        );
        ;
        Page<Pork> pageResult = porkRepository.findAll(spec, pageable);

        return new ShortAdsResponse(
                adMapper.toListShortPorkAdResponse(pageResult.getContent()),
                pageResult.getTotalElements()
        );
    }

    private static Specification<Pork> getPorkSpecification(PorkAdsRequest request) {
        Specification<Pork> spec = Specification.where(null);

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

    public PorkAdResponse findById(long id) {
        Pork pork = porkRepository.findById(id).orElseThrow(() -> new NotFoundException(Pork.class, id));
        return adMapper.toPorkAdResponse(pork);
    }

    public PorkAdResponse createPorkAd(PorkAdSaveRequest request, String email) {
        Pork newPork = adMapper.toPork(request);
        newPork.setSellerUser(userRepository.findByEmail(email).orElseThrow(
                () -> new InternalServerErrorException("Несущестующий пользователь пытается создать объявление")
        ));
        Pork pork = porkRepository.save(newPork);

        return adMapper.toPorkAdResponse(pork);
    }

    public PorkAdResponse patchById(long id, PorkAdSaveRequest request, String email) {
        Pork pork = porkRepository.findById(id).orElseThrow(() -> new NotFoundException(Pork.class, id));
        validatePorkAdOwner(email, pork);
        adMapper.updatePorkFromPorkAdSaveRequest(pork, request);
        porkRepository.save(pork);

        return adMapper.toPorkAdResponse(pork);
    }

    public void deleteById(long id, String email) {
        Pork pork = porkRepository.findById(id).orElseThrow(() -> new NotFoundException(Pork.class, id));
        validatePorkAdOwner(email, pork);
        porkRepository.deleteById(id);
    }

    private void validatePorkAdOwner(String email, Pork pork) {
        var sellerUser = pork.getSellerUser();

        if (!sellerUser.getEmail().equals(email)) {
            throw new ForbiddenAccessException(email);
        }
    }
}