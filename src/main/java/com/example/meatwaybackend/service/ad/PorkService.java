package com.example.meatwaybackend.service.ad;

import com.example.meatwaybackend.dao.ad.AdvertisementRepository;
import com.example.meatwaybackend.dao.ad.PorkRepository;
import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdSaveRequest;
import com.example.meatwaybackend.dto.ad.pork.PorkAdsRequest;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.AdMapper;
import com.example.meatwaybackend.mapper.UserMapper;
import com.example.meatwaybackend.model.ad.Pork;
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
    private final PorkRepository porkRepository;
    private final AdMapper adMapper;
    private final AdvertisementRepository advertisementRepository;

    public ShortAdsResponse findAll(int page, int size, String sort, PorkAdsRequest request) {
        Specification<Pork> spec = getPorkSpecification(request);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Pork> pageResult = porkRepository.findAll(spec, pageable);

        return new ShortAdsResponse(
                adMapper.toListShortPorkAdResponse(pageResult.getContent()),
                pageResult.getTotalElements()
        );
    }

    private static Specification<Pork> getPorkSpecification(PorkAdsRequest request) {
        Specification<Pork> spec = Specification.where(null);

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

    public PorkAdResponse createPorkAd(PorkAdSaveRequest request) {
        Pork pork = porkRepository.save(adMapper.toPork(request));
        return adMapper.toPorkAdResponse(pork);
    }

    public PorkAdResponse patchById(long id, PorkAdSaveRequest request) {
        Pork pork = porkRepository.findById(id).orElseThrow(() -> new NotFoundException(Pork.class, id));
        adMapper.updatePorkFromPorkAdSaveRequest(pork, request);
        return adMapper.toPorkAdResponse(pork);
    }

    public void deleteById(long id) {
        advertisementRepository.findById(id).orElseThrow(() -> new NotFoundException(Pork.class, id));
        advertisementRepository.deleteById(id);
    }
}