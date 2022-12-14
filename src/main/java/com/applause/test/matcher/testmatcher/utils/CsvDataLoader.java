package com.applause.test.matcher.testmatcher.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Component
@EnableAutoConfiguration
public class CsvDataLoader {

  public <T> List<T> loadObjectList(Class<T> type, String fileName) {
    Logger logger = LoggerFactory.getLogger(CsvDataLoader.class);
    try {
      CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
      CsvMapper mapper = new CsvMapper();
      File file = new ClassPathResource(fileName).getFile();
      MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
      return readValues.readAll();
    } catch (Exception e) {
      logger.error("Error occurred while loading object list from file " + fileName, e);
      return Collections.emptyList();
    }
  }
}
