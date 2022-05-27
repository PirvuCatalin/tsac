package ro.catalinpirvu.tsac.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomService {
    private final SecureRandom generator = new SecureRandom();
    public Integer getRandomNumber(Integer upperBound) {
        if (upperBound == null) {
            return generator.nextInt();
        }
        return generator.nextInt(upperBound);
    }
}
