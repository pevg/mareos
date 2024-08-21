package com.pevg.www.envios.services;

import com.pevg.www.envios.dtos.ProductSummary;
import com.pevg.www.envios.dtos.ShippingStatus;
import com.pevg.www.envios.dtos.TransitionRequest;
import com.pevg.www.envios.entities.Shipping;
import com.pevg.www.envios.enums.ShippingState;
import com.pevg.www.envios.exceptions.InvalidTransitionException;
import com.pevg.www.envios.repositories.ShippingItemRepository;
import com.pevg.www.envios.repositories.ShippingRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShippingService {

    @Autowired
    private final ShippingRepository shippingRepository;
    @Autowired
    private final ShippingItemRepository shippingItemRepository;

    private static final Map<ShippingState, ShippingState[]> stateTransitions = new HashMap<>();

    static {
        stateTransitions.put(ShippingState.INICIAL, new ShippingState[]{ShippingState.ENTREGADO_AL_CORREO, ShippingState.CANCELADO});
        stateTransitions.put(ShippingState.ENTREGADO_AL_CORREO, new ShippingState[]{ShippingState.EN_CAMINO, ShippingState.CANCELADO});
        stateTransitions.put(ShippingState.EN_CAMINO, new ShippingState[]{ShippingState.ENTREGADO, ShippingState.CANCELADO});
    }

    public ResponseEntity<List<Shipping>> getAll() {
        List<Shipping> shippins = shippingRepository.findAll();
        return new ResponseEntity<>(shippins, HttpStatus.OK);
    }

    public ResponseEntity<List<ShippingStatus>> getStatus() {
        List<Shipping> shippins = shippingRepository.findAll();

        List<ShippingStatus> status = shippins.stream()
        .map(shipping -> new ShippingStatus(shipping.getId(), shipping.getState()))
        .collect(Collectors.toList());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    public ResponseEntity<Shipping> getById(int id) {
        Optional<Shipping> shipping = shippingRepository.findById(id);
        return shipping.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    public Shipping changeState(int id, TransitionRequest request){

        Shipping shipping = shippingRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró envío con el id suministrado"));
        ShippingState currentState = ShippingState.valueOf(shipping.getState().toUpperCase());
        ShippingState newState;
        try{
            newState = ShippingState.valueOf(request.getTransition().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTransitionException("Estado de transición incorrecto: " + request.getTransition());
        }
        validateTransition(currentState, newState);
        shipping.setState(String.valueOf(newState).toLowerCase());
        return shippingRepository.save(shipping);
    }

    private void validateTransition(ShippingState currentState, ShippingState newState) throws InvalidTransitionException {
        if (!stateTransitions.containsKey(currentState) || !Arrays.asList(stateTransitions.get(currentState)).contains(newState)) {
            throw new InvalidTransitionException("No es posible hacer transición de estado " + currentState + " a " + newState);
        }
    }

    public List<ProductSummary> getTopProducts() {
        return shippingItemRepository.findTopProducts();
    }

}
