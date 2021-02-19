package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {

    private final DataModelService dataModelService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        DataModel dataModel = new DataModel();

        dataModel.setName("Test");
        dataModel.setDescription("descr");

        IncrementField field1 = new IncrementField();
        field1.setFieldName("test");
        field1.setDataModel(dataModel);

        IncrementField field2 = new IncrementField();
        field2.setFieldName("test2");
        field2.setDataModel(dataModel);

        OutputField outputField1 = new OutputField();
        outputField1.setFieldName("test1");
        outputField1.setDataModel(dataModel);


        OutputField outputField2 = new OutputField();
        outputField2.setFieldName("test2");
        outputField2.setDataModel(dataModel);

        OutputField outputField3 = new OutputField();
        outputField3.setFieldName("test3");
        outputField3.setDataModel(dataModel);

        dataModel.setIncrementFields(List.of(
                field1
        ));
        dataModel.setOutputFields(List.of(
                outputField1, outputField2, outputField3
        ));
        DataModel save = dataModelService.save(dataModel);

        DataModel byId = dataModelService.getById(save.getId());

        byId.getOutputFields().removeIf(it -> it.getFieldName().equals("test2"));

        dataModelService.save(byId);

        //DataModel byId1 = dataModelService.getById(dataModel.getId());

        return ResponseEntity.ok("Hello");
    }

}
