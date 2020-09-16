package co.com.arqsoft.demodecouplingspring.component.user.service;

import co.com.arqsoft.demodecouplingspring.component.user.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserGateway userGateway;

    @Override
    public User findById(@NotNull Long id) {
        logger.debug("Begin findById = {}", id);

        final User userFound = userGateway.findById(id);

        logger.debug("End findById: userFound = {}", userFound);
        return userFound;
    }
}
