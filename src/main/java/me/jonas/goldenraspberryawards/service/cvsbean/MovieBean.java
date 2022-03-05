package me.jonas.goldenraspberryawards.service.cvsbean;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieBean {

    @CsvBindByName
    private int year;

    @CsvBindByName
    private String title;

    @CsvBindByName
    private String studios;

    @CsvBindByName
    private String producers;

    @CsvBindByName()
    private String winner;

}
