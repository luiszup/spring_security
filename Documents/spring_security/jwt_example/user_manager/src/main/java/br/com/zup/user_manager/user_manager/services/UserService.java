package br.com.zup.user_manager.user_manager.services;

import br.com.zup.user_manager.user_manager.dtos.UserLoginDTO;
import br.com.zup.user_manager.user_manager.models.UserModel;
import br.com.zup.user_manager.user_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

// Indica que esta classe é um serviço do Spring, responsável por conter a lógica de negócios
@Service
public class UserService {

    private UserRepository userRepository; // Repositório para interagir com o banco de dados
    private PasswordEncoder bCryptPasswordEncoder; // Codificador de senhas para segurança

    // Construtor que injeta as dependências necessárias
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder; // Inicializa o codificador de senhas
        this.userRepository = userRepository; // Inicializa o repositório de usuários
    }

    /**
     * Salva um usuário no banco de dados.
     * Antes de salvar, a senha do usuário é codificada para garantir segurança.
     *
     * @param user Objeto do tipo UserModel contendo os dados do usuário.
     * @return O usuário salvo no banco de dados.
     */
    public UserModel saveUser(UserModel user){
        // Codifica a senha do usuário
        String passwordEncoder = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder); // Define a senha codificada no objeto do usuário
        return userRepository.save(user); // Salva o usuário no banco de dados
    }

    /**
     * Realiza o login de um usuário.
     * Verifica se o username existe no banco de dados e se a senha fornecida é válida.
     *
     * @param userLoginDTO Objeto contendo os dados de login (username e senha).
     * @return Um mapa contendo uma mensagem de sucesso ou erro.
     */
    public Map<String, String> login(UserLoginDTO userLoginDTO){
        // Busca o usuário no banco de dados pelo username
        Optional<UserModel> userOptional = userRepository.findByUserName(userLoginDTO.getUserName());
        // Verifica se o usuário foi encontrado
        if(userOptional.isPresent()){
            UserModel user = userOptional.get(); // Obtém o usuário encontrado

            // Verifica se a senha fornecida corresponde à senha armazenada (codificada)
            if(bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())){
                return Map.of("mensagem:", "usuario logado"); // Retorna mensagem de sucesso
            }

            return Map.of("mensagem:", "senha invalida"); // Retorna mensagem de senha inválida
        }

        return Map.of("mensagem:", "userName invalido"); // Retorna mensagem de username inválido
    }
}
