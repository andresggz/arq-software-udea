package co.com.arqsoft.demodecouplingspring.component.user.io.gateway;

import co.com.arqsoft.demodecouplingspring.component.shared.web.exception.NotFoundException;
import co.com.arqsoft.demodecouplingspring.component.user.io.repository.UserRepository;
import co.com.arqsoft.demodecouplingspring.component.user.model.User;
import co.com.arqsoft.demodecouplingspring.component.user.service.UserGateway;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;


@Repository
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    @Override
    public User findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        final User userFound = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id = "+ id + " not found"));

        logger.debug("End findById: userFound = {}", userFound);
        return null;
    }
}
