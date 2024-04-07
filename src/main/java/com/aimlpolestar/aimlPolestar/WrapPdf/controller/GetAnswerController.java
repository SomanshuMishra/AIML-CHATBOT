package com.aimlpolestar.aimlPolestar.WrapPdf.controller;
import com.aimlpolestar.aimlPolestar.WrapPdf.service.GenerateTextService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GetAnswerController {

    @Autowired
    private GenerateTextService generateTextService;

    @PostMapping("/get_answer")
    public String getAnswer(@RequestBody String inputQuery) {

        return generateTextService.getAnswer(inputQuery);
    }

}
