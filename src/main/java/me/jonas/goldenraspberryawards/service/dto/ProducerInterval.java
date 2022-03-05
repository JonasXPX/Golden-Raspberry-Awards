package me.jonas.goldenraspberryawards.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ProducerInterval {

    public ProducerInterval(final String producer, final Optional<Integer> previousWin, final Optional<Integer>  followingWin) {
        this.producer = producer;
        this.previousWin = previousWin.orElse(null);
        this.followingWin = followingWin.orElse(null);
    }

    private String producer;

    private int interval;

    private int previousWin;

    private int followingWin;

    public int getInterval() {
        return followingWin - previousWin;
    }

    @Override
    public String toString() {
        return "ProducerInterval{" +
                "producer='" + producer + '\'' +
                ", interval=" + getInterval() +
                ", previousWin=" + previousWin +
                ", followingWin=" + followingWin +
                "}\n";
    }
}
