package com.spring.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.enums.StuffIdType;
import com.spring.models.StuffIdModel;
import com.spring.repositories.StuffIdRepository;

@Service
public class StuffIdService {
    private final StuffIdRepository stuffIdRepository;

    public StuffIdService(StuffIdRepository stuffIdRepository) {
        this.stuffIdRepository = stuffIdRepository;
    }

    public List<StuffIdModel> getAllStuffIds() {
        if (stuffIdRepository.count() < 50) {
            loadStuffIdsFromCSVFiles();
        }
        return stuffIdRepository.findAll();
    }

    public void createStuffId(StuffIdModel stuffId) {
        stuffIdRepository.save(stuffId);
    }

    private void loadStuffIdsFromCSVFiles() {
        loadStuffIdsFromCSVFile(new File("media/stuff-id-files/FileStuffCSV1.csv"));
        loadStuffIdsFromCSVFile(new File("media/stuff-id-files/FileStuffCSV5.csv"));
    }

    private void loadStuffIdsFromCSVFile(File file) {
        final String INTERNAL = "داخلي";
        final String IMPORTED = "وارداتي";
        final String PRIVATE = "اختصاصي";
        final String PUBLIC = "عمومي";
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            List<StuffIdModel> stuffIdList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    String stuffId = data[0].trim();
                    String name = data[1].trim();
                    int vatRate = Integer.parseInt(data[2].trim());
                    LocalDate registrationDate = LocalDate.parse(data[3].trim(), formatter);
                    String stuffType = data[4].trim();

                    // Data cleaning rules
                    StuffIdType stuffIdType = null;
                    boolean isPrivate = false;
                    String[] tempType = stuffType.split("-");
                    if (tempType.length == 1) {
                        if (tempType[0].trim().equals(PRIVATE)) {
                            stuffIdType = StuffIdType.PRIVATE_SERVICE_ID;
                            isPrivate = true;
                        } else if (tempType[0].trim().equals(PUBLIC)) {
                            stuffIdType = StuffIdType.PUBLIC_SERVICE_ID;
                            isPrivate = false;
                        }
                    }
                    if (tempType.length == 2) {
                        if (tempType[0].trim().equals(PRIVATE)) {
                            isPrivate = true;
                            if (tempType[1].trim().equals(INTERNAL)) {
                                stuffIdType = StuffIdType.PRIVATE_INTERNAL_STUFF_ID;
                            } else if (tempType[1].trim().equals(IMPORTED)) {
                                stuffIdType = StuffIdType.PRIVATE_IMPORTED_STUFF_ID;
                            }
                        } else if (tempType[0].trim().equals(PUBLIC)) {
                            isPrivate = false;
                            if (tempType[1].trim().equals(INTERNAL)) {
                                stuffIdType = StuffIdType.PUBLIC_INTERNAL_STUFF_ID;
                            } else if (tempType[1].trim().equals(IMPORTED)) {
                                stuffIdType = StuffIdType.PUBLIC_IMPORTED_STUFF_ID;
                            }
                        }
                    }

                    StuffIdModel stuffIdModel = new StuffIdModel();
                    stuffIdModel.setName(name);
                    stuffIdModel.setPrivateId(isPrivate);
                    stuffIdModel.setRegistrationDate(registrationDate);
                    stuffIdModel.setStuffId(stuffId);
                    stuffIdModel.setStuffIdType(stuffIdType);
                    stuffIdModel.setVatRate(vatRate);

                    stuffIdList.add(stuffIdModel);
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }

            stuffIdRepository.saveAll(stuffIdList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse CSV: " + e.getMessage());
        }
    }
}
