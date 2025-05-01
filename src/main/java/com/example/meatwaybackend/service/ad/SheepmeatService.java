package com.example.meatwaybackend.service.ad;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dao.ad.AdvertisementRepository;
import com.example.meatwaybackend.dao.ad.SheepmeatRepository;
import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdResponse;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdSaveRequest;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdsRequest;
import com.example.meatwaybackend.handler.exception.InternalServerErrorException;
import com.example.meatwaybackend.handler.exception.auth.ForbiddenAccessException;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.AdMapper;
import com.example.meatwaybackend.model.ad.Beef;
import com.example.meatwaybackend.model.ad.Sheepmeat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SheepmeatService {
    private final SheepmeatRepository sheepmeatRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;

    public ShortAdsResponse findAll(int page, int size, String sort, SheepmeatAdsRequest request) {
        Specification<Sheepmeat> spec = getSheepmeatSpecification(request);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Sheepmeat> pageResult = sheepmeatRepository.findAll(spec, pageable);

        return new ShortAdsResponse(
                adMapper.toListShortSheepmeatAdResponse(pageResult.getContent()),
                pageResult.getTotalElements()
        );
    }

    private static Specification<Sheepmeat> getSheepmeatSpecification(SheepmeatAdsRequest request) {
        Specification<Sheepmeat> spec = Specification.where(null);

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

    public SheepmeatAdResponse findById(long id) {
        Sheepmeat sheepmeat = sheepmeatRepository.findById(id).orElseThrow(() -> new NotFoundException(Sheepmeat.class, id));
        return adMapper.toSheepmeatAdResponse(sheepmeat);
    }

    public SheepmeatAdResponse createSheepmeatAd(SheepmeatAdSaveRequest request, String email) {
        Sheepmeat newSheepmeat = adMapper.toSheepmeat(request);
        newSheepmeat.setSellerUser(userRepository.findByEmail(email).orElseThrow(
                () -> new InternalServerErrorException("Несущестующий пользователь пытается создать объявление")
        ));
        Sheepmeat sheepmeat = sheepmeatRepository.save(newSheepmeat);

        return adMapper.toSheepmeatAdResponse(sheepmeat);
    }

    public SheepmeatAdResponse patchById(long id, SheepmeatAdSaveRequest request, String email) {
        Sheepmeat sheepmeat = sheepmeatRepository.findById(id).orElseThrow(() -> new NotFoundException(Sheepmeat.class, id));
        validateSheepmeatAdOwner(email, sheepmeat);
        adMapper.updateSheepmeatFromSheepmeatAdSaveRequest(sheepmeat, request);

        return adMapper.toSheepmeatAdResponse(sheepmeat);
    }

    public void deleteById(long id, String email) {
        Sheepmeat sheepmeat = sheepmeatRepository.findById(id).orElseThrow(() -> new NotFoundException(Sheepmeat.class, id));
        validateSheepmeatAdOwner(email, sheepmeat);
        sheepmeatRepository.deleteById(id);
    }

    private void validateSheepmeatAdOwner(String email, Sheepmeat sheepmeat) {
        var sellerUser = sheepmeat.getSellerUser();

        if (!sellerUser.getEmail().equals(email)) {
            throw new ForbiddenAccessException(email);
        }
    }
}