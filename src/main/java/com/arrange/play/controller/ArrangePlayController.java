package com.arrange.play.controller;

import com.arrange.play.model.FlowRequestDTO;
import com.arrange.play.model.FlowResponseDTO;
import com.arrange.play.service.ArrangePlayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/arrang")
public class ArrangePlayController {

  @Autowired
  private ArrangePlayService arrangePlayService;

  @PostMapping(value = "/play")
  public FlowResponseDTO doFlow(@RequestBody FlowRequestDTO flowRequestDTO) {
      return arrangePlayService.flow(flowRequestDTO);
  }


}
