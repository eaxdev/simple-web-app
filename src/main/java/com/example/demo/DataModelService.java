package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DataModelService {

    private final DataModelRepository dataModelRepository;

    @Transactional
    public DataModel save(DataModel dataModel) {
        DataModel save = dataModelRepository.save(dataModel);
        return save;
    }

    @Transactional(readOnly = true)
    public DataModel getById(int id) {
        DataModel save = dataModelRepository.findByIdIncludeOutputFields(id).get();
        return save;
    }

}
