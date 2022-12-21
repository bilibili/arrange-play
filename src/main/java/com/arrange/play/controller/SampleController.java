package com.arrange.play.controller;

import com.arrange.play.model.sample.SampleRequestDTO;
import com.arrange.play.model.sample.SampleResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/sample")
public class SampleController {

  @GetMapping("/getSample")
  public SampleResponseDTO getSample(SampleRequestDTO sampleRequestDTO) {
    SampleResponseDTO sampleResponseDTO = new SampleResponseDTO();
    sampleResponseDTO.setResName(sampleRequestDTO.getName());
    sampleResponseDTO.setResContent("testContent");
    return sampleResponseDTO;
  }

  @PostMapping("/postSample")
  public SampleResponseDTO postSample(@RequestBody SampleRequestDTO sampleRequestDTO) {
    SampleResponseDTO sampleResponseDTO = new SampleResponseDTO();
    sampleResponseDTO.setResName(sampleRequestDTO.getName());
    sampleResponseDTO.setResContent(sampleRequestDTO.getValue());
    return sampleResponseDTO;
  }

}
