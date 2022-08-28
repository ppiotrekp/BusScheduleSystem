package pl.ppyrczak.busschedulesystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ppyrczak.busschedulesystem.model.Bus;
import pl.ppyrczak.busschedulesystem.repository.BusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public List<Bus> getBuses() {
        return busRepository.findAll();
    }

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public Bus getBus(Long id) {
        return busRepository.findById(id).orElseThrow();
    }
}