package com.BE.Domain.Card.Service;

import com.BE.Domain.Card.Entity.Card;
import com.BE.Domain.Card.Repository.CardRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
@Transactional
public class CSVService {

    private final CardRepository cardRepository;

    public CSVService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostConstruct
    public void init() {
        // 애플리케이션 시작 시 데이터를 로드
        loadDataFromCSV("card_data.csv");
    }

    @Transactional
    public void loadDataFromCSV(String fileName) {
        try {
            // CSV 파일 읽기
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (resource == null) {
                throw new IllegalArgumentException("파일을 찾을 수 없습니다: " + fileName);
            }

            File file = new File(resource.getFile());
            try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
                String[] nextRecord;
                int rowCount = 0;

                // 첫 번째 줄(헤더)을 스킵
                csvReader.readNext();

                while ((nextRecord = csvReader.readNext()) != null) {
                    // CSV 데이터 매핑
                    Card card = Card.builder()
                            .name(nextRecord[1])          // 두 번째 컬럼: 카드 이름
                            .companyName(nextRecord[2])   // 세 번째 컬럼: 카드사 이름
                            .benefit(nextRecord[3])       // 네 번째 컬럼: 카드 혜택
                            .imageUrl(nextRecord[4])      // 다섯 번째 컬럼: 카드 이미지 URL
                            .build();

                    // 데이터베이스에 저장
                    cardRepository.save(card);
                    rowCount++;
                }

                System.out.println("총 " + rowCount + "개의 데이터를 성공적으로 저장했습니다.");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("CSV 데이터를 읽는 중 오류가 발생했습니다.");
        }
    }
}
