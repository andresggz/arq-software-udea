package co.com.arqsoft.demodecouplingspring.component.user.service;

import co.com.arqsoft.demodecouplingspring.component.user.model.User;

import javax.validation.constraints.NotNull;

public interface UserService {

    User findById(@NotNull Long id);
}
