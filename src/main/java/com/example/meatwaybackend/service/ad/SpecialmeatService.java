package com.example.meatwaybackend.service.ad;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dao.ad.SpecialmeatRepository;
import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdResponse;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdSaveRequest;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdsRequest;
import com.example.meatwaybackend.handler.exception.InternalServerErrorException;
import com.example.meatwaybackend.handler.exception.auth.ForbiddenAccessException;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.AdMapper;
import com.example.meatwaybackend.model.ad.Specialmeat;
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
public class SpecialmeatService {
    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_SIZE = 10;
    private final static String DEFAULT_SORT = "id";

    private final SpecialmeatRepository specialmeatRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;

    public ShortAdsResponse findAll(Integer page, Integer size, String sort, SpecialmeatAdsRequest request) {
        Specification<Specialmeat> spec = getSpecialmeatSpecification(request);
        if (sort == null || sort.isBlank()) {
            sort = DEFAULT_SORT;
        }

        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(DEFAULT_PAGE),
                Optional.ofNullable(size).orElse(DEFAULT_SIZE),
                Sort.by(sort)
        );
        Page<Specialmeat> pageResult = specialmeatRepository.findAll(spec, pageable);

        return new ShortAdsResponse(
                adMapper.toListShortSpecialmeatAdResponse(pageResult.getContent()),
                pageResult.getTotalElements()
        );
    }

    private static Specification<Specialmeat> getSpecialmeatSpecification(SpecialmeatAdsRequest request) {
        Specification<Specialmeat> spec = Specification.where(null);

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

    public SpecialmeatAdResponse findById(long id) {
        Specialmeat specialmeat = specialmeatRepository.findById(id).orElseThrow(() -> new NotFoundException(Specialmeat.class, id));
        return adMapper.toSpecialmeatAdResponse(specialmeat);
    }

    public SpecialmeatAdResponse createSpecialmeatAd(SpecialmeatAdSaveRequest request, String email) {
        Specialmeat newSpecialmeat = adMapper.toSpecialmeat(request);
        newSpecialmeat.setSellerUser(userRepository.findByEmail(email).orElseThrow(
                () -> new InternalServerErrorException("Несущестующий пользователь пытается создать объявление")
        ));
        Specialmeat specialmeat = specialmeatRepository.save(newSpecialmeat);

        return adMapper.toSpecialmeatAdResponse(specialmeat);
    }

    public SpecialmeatAdResponse patchById(Long id, SpecialmeatAdSaveRequest request, String email) {
        Specialmeat specialmeat = specialmeatRepository.findById(id).orElseThrow(() -> new NotFoundException(Specialmeat.class, id));
        validateSpecialmeatAdOwner(email, specialmeat);
        adMapper.updateSpecialmeatFromSpecialmeatAdSaveRequest(specialmeat, request);
        specialmeatRepository.save(specialmeat);

        return adMapper.toSpecialmeatAdResponse(specialmeat);
    }

    public void deleteById(long id, String email) {
        Specialmeat specialmeat = specialmeatRepository.findById(id).orElseThrow(() -> new NotFoundException(Specialmeat.class, id));
        validateSpecialmeatAdOwner(email, specialmeat);
        specialmeatRepository.deleteById(id);
    }

    private void validateSpecialmeatAdOwner(String email, Specialmeat specialmeat) {
        var sellerUser = specialmeat.getSellerUser();

        if (!sellerUser.getEmail().equals(email)) {
            throw new ForbiddenAccessException(email);
        }
    }
}