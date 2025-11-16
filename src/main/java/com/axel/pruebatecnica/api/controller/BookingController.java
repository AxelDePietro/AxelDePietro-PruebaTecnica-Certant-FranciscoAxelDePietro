package com.axel.pruebatecnica.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.service.implementations.BookingService;
import com.axel.pruebatecnica.api.service.implementations.EventService;
import com.axel.pruebatecnica.api.service.implementations.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final EventService eventService;

    //vista de reservas
    @GetMapping("/createBookingConcert/{idEvent}")
    ModelAndView createViewConcert(@PathVariable int idEvent) {
    	
    	EventEntity event = eventService.findById(idEvent);
        
        ModelAndView mav = new ModelAndView("booking/createBookingConcert");
        
        mav.addObject("event", event);

        return mav;
    }
    
    @GetMapping("/createBookingTheater/{idEvent}")
    ModelAndView createViewTheater(@PathVariable int idEvent) {
    	
    	EventEntity event = eventService.findById(idEvent);
        
        ModelAndView mav = new ModelAndView("booking/createBookingTheater");
        
        mav.addObject("event", event);

        return mav;
    }
    
    @GetMapping("/createBookingConference/{idEvent}")
    ModelAndView createViewConference(@PathVariable int idEvent) {
    	
        EventEntity event = eventService.findById(idEvent);
        
        ModelAndView mav = new ModelAndView("booking/createBookingConference");
        
        mav.addObject("event", event);

        return mav;
    }

//    //crear reserva
//    @PostMapping("/createBooking")
//    ModelAndView createBooking(
//            @AuthenticationPrincipal User user,
//            @ModelAttribute BookingEntity booking,
//            @RequestParam int idEvent,
//            @RequestParam String seatType) {
//
//        UserEntity userAux = userService.findByUsername(user.getUsername());
//        bookingService.create(booking, idEvent, userAux.getIdUser(), seatType);
//
//        ModelAndView mav = new ModelAndView("user/misReservas");
//        mav.addObject("mybookings", bookingService.myBookings(userAux.getIdUser()));
//        return mav;
//    }
//
//    //todas las reservas
//    @GetMapping("/allBooking")
//    ModelAndView allBookings() {
//        ModelAndView mav = new ModelAndView("booking/allBooking");
//
//        List<BookingEntity> bookings = bookingService.bookings();
//        mav.addObject("bookings", bookings);
//        return mav;
//    }
//
//    // si fuera rest seria DeleteMapping (solo admin)
//    @PostMapping("/deleteBooking/{idBooking}")
//    ModelAndView deleteBooking(@PathVariable int idBooking) {
//
//        bookingService.delete(idBooking);
//    	
//        return allBookings();
//    }

}
