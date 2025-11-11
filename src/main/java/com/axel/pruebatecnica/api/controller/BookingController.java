package com.axel.pruebatecnica.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

//    private final BookingService bookingService;
//    private final UserService userService;
//    private final EventService eventService;
//
//    //vista de reservas
//    @GetMapping("/createBooking/{idEvent}")
//    ModelAndView createView(@PathVariable int idEvent) {
//    	
//    	//encuentra el evnto por id para mostrar datos y tomar ls seatCapacity
//        EventEntity event = eventService.findById(idEvent);
//        
//        ModelAndView mav = new ModelAndView("booking/createBooking");
//        
//        mav.addObject("idEvent", idEvent);
//        mav.addObject("event", event);
//        mav.addObject("capacidades", event.getAvailableCapacities());
//
//        return mav;
//    }
//
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
