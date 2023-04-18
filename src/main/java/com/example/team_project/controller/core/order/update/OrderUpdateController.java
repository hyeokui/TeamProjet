package com.example.team_project.controller.core.order.update;

import com.example.team_project.domain.domain.address.domain.UserAddress;
import com.example.team_project.domain.domain.address.domain.UserAddressRepository;
import com.example.team_project.domain.domain.order.item.domain.Order;
import com.example.team_project.domain.domain.order.item.domain.OrderRepository;
import com.example.team_project.domain.domain.order.item.service.OrderUpdateServiceImpl;
import com.example.team_project.domain.domain.order.list.domain.OrderList;
import com.example.team_project.domain.domain.order.list.domain.OrderListRepository;
import com.example.team_project.domain.domain.order.list.service.OrderListUpdateServiceImpl;
import com.example.team_project.domain.domain.user.domain.User;
import com.example.team_project.exception.InvalidAddressException;
import com.example.team_project.exception.OrderListNotFoundException;
import com.example.team_project.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/order_list/update_address")
public class OrderUpdateController {

    private final OrderListUpdateServiceImpl orderListUpdateService;
    private final UserAddressRepository userAddressRepository;
    private final OrderListRepository orderListRepository;

    @GetMapping
    public ModelAndView updateForm(@SessionAttribute("userId") Long userId, @RequestParam Long orderListId) {
        List<UserAddress> userAddressList = userAddressRepository.findByUserId(userId);
        OrderList orderList = orderListRepository.findById(orderListId).orElseThrow(OrderListNotFoundException::new);

        ModelAndView modelAndView = new ModelAndView("thymeleaf/order/order_address_update");
        modelAndView.addObject("user_address_list", userAddressList);
        modelAndView.addObject("orderList", orderList);

        return modelAndView;
    }

    @PostMapping
    public ModelAndView update(@SessionAttribute("userId") Long userId, @RequestParam Long orderListId, @RequestParam Long userAddressId) {
        orderListUpdateService.update(userId, orderListId, userAddressId);

        return new ModelAndView("redirect:/order_list/view/");
    }
}
