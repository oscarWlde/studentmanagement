//package com.example.demo.controller;
//
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//@RestController
//@RequestMapping("/web")
//public class Maincontroller {
//
//
//
//    /**
//
//     * 直播页面
//
//     * @param modelMap
//
//     * @return
//
//     */
//
//    @GetMapping("webBroadcast")
//    public ModelAndView webBroadcast(ModelMap modelMap){
//        return new ModelAndView("/broadcast", modelMap);
//    }
//
//
//
//
//
//    /**
//
//     * 观看页面
//
//     * @param modelMap
//
//     * @return
//
//     */
//
//    @GetMapping("webWatch")
//
//    public ModelAndView webWatch(ModelMap modelMap){
//
//        return new ModelAndView("/watch", modelMap);
//
//    }
//
//
//
//}