package com.bancaonline.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Random;

import com.bancaonline.api.model.AuthDevice;
import com.bancaonline.api.model.Consortium;
import com.bancaonline.api.model.ConsortiumToken;
import com.bancaonline.api.model.Status;
import com.bancaonline.api.model.TokenType;
import com.bancaonline.api.repository.AuthDeviceRepository;
import com.bancaonline.api.repository.ConsortiumRepository;
import com.bancaonline.api.repository.ConsortiumTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsortiumTokenService {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsortiumService.class);

    @Autowired
    private ConsortiumTokenRepository consortiumTokenRepository;

    @Autowired
    private AuthDeviceRepository authDeviceRepository;

    @Autowired
    private ConsortiumRepository consortiumRepository;

    public List<ConsortiumToken> findAll() {

        List<ConsortiumToken> list = new ArrayList<ConsortiumToken>();
        Iterable<ConsortiumToken> consortiumToken = consortiumTokenRepository.findAll();
        consortiumToken.forEach(list::add);
        LOGGER.info("GetAllConsortiumToken was completed");
        return list;
    }

    public Optional<ConsortiumToken> findByTokenAndStatusId(String token, Long statusId) {
        return consortiumTokenRepository.findByTokenAndStatusId(token, statusId);
    }

    public Optional<ConsortiumToken> findByShortUrl(String shortUrl) {
        return consortiumTokenRepository.findByShortUrl(shortUrl);
    }

    public Optional<ConsortiumToken> findByShortToken(String shortToken) {
        return consortiumTokenRepository.findByShortToken(shortToken);
    }

    public List<ConsortiumToken> findByConsortiumId(Long id) {
        return consortiumTokenRepository.findByConsortiumId(id);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Long id) {
        LOGGER.info("ConsortiumToken was deleted");
        consortiumTokenRepository.delete(id);
    }

    /**
     * Create token boolean.
     *
     * @param name      the name
     * @param tokenType the token type
     * @return the boolean
     */
    public ConsortiumToken createToken(String name, Long tokenType) {
        String urlTemplate = "http://{s3_bucket_name}.s3-website-us-east-1.amazonaws.com/{html_page}?token={device_token}";
        Optional<Consortium> consortium = consortiumRepository.findByNameAndStatusId(name, Status.Type.ENABLED.getId());
        ConsortiumToken ct = new ConsortiumToken();
        if (consortium.isPresent()) {
            String token = UUID.randomUUID().toString();
            Random random = new Random();

            String[] tokenParts = token.split("-");
            String shortToken = tokenParts[0].substring(0, 1) + tokenParts[1].substring(0, 1)
                    + tokenParts[2].substring(0, 1) + tokenParts[3].substring(0, 1) + tokenParts[4].substring(0, 1)
                    + random.nextInt(10);

            String shortUrl = urlTemplate.replace("{s3_bucket_name}", consortium.get().getS3BucketName())
                    .replace("{html_page}", "redirect.html").replace("{device_token}", shortToken)
                    .replace("token", "shortToken");

            String fullUrl = urlTemplate.replace("{s3_bucket_name}", consortium.get().getS3BucketName())
                    .replace("{html_page}", "").replace("{device_token}", token);

            ct.setConsortium(consortium.get());
            ct.setToken(token);
            ct.setStatus(Status.Type.ENABLED.toStatus());
            ct.setTokenType(new TokenType(tokenType));
            ct.setCreatedDate(LocalDateTime.now());
            ct.setShortUrl(shortUrl);
            ct.setShortToken(shortToken);
            ct.setFullUrl(fullUrl);
            ct = consortiumTokenRepository.save(ct);

        }

        return ct;
    }

    /**
     * Validate device boolean.
     *
     * @param ip    the ip
     * @param token the token
     * @return the boolean
     */
    public boolean validateDevice(String ip, String token) {

        boolean result = true;
        Optional<ConsortiumToken> consortiumToken = this.consortiumTokenRepository.findByTokenAndStatusId(token,
                Status.Type.ENABLED.getId());

        boolean isPresent = consortiumToken.isPresent();
        if (isPresent && consortiumToken.get().getStatus().getId() == Status.Type.ENABLED.getId()) {

            Optional<AuthDevice> device = authDeviceRepository.findByTokenAndIpAndStatus(token, ip,
                    Status.Type.ENABLED.toStatus());

            if (device.isEmpty()) {

                if (!this.authDeviceRepository.existsByIpAndToken(ip, token)) {

                    List<AuthDevice> ads = this.authDeviceRepository.findByTokenAndStatus(token,
                            Status.Type.ENABLED.toStatus());

                    ads.forEach(ad -> {

                        ad.setStatus(Status.Type.DISABLED.toStatus());
                        this.authDeviceRepository.save(ad);
                    });

                    AuthDevice ad = new AuthDevice(ip, token, Status.Type.ENABLED.toStatus());
                    ad.setCreatedDate(LocalDateTime.now());
                    authDeviceRepository.save(ad);

                } else {

                    result = false;
                }

            }

        } else {

            result = false;
        }

        return result;
    }

    public String findFullUrl(String shortToken) {
        String fullUrl = "";
        Optional<ConsortiumToken> token = consortiumTokenRepository.findByShortToken(shortToken);
        if (token.isPresent()) {
            fullUrl = token.get().getFullUrl();
        }
        return fullUrl;
    }

}