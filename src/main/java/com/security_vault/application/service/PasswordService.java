package com.security_vault.application.service;

import com.security_vault.adapters.dto.PasswordFindAllResponseDto;
import com.security_vault.adapters.dto.PasswordGenerateResponseDto;
import com.security_vault.adapters.repository.PasswordRepository;
import com.security_vault.domain.model.Password;
import com.security_vault.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;
    private final UserService userService;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository, UserService userService) {
        this.passwordRepository = passwordRepository;
        this.userService = userService;
    }

    public PasswordGenerateResponseDto createPassword(String title, String user_id) {

        String generatedString = this.generatePassword(title);

        Users findUser = userService.findOne(user_id, null);

        Password newPassword = new Password();

        newPassword.setPassword(generatedString);
        newPassword.setName(title);
        newPassword.setUser(findUser);

        passwordRepository.save(newPassword);

        return new PasswordGenerateResponseDto(title, generatedString);

    }

    public List<PasswordFindAllResponseDto> findAllPassword(String id_user) {

        List<Password> passwords = passwordRepository.findAllByIdUser(id_user);

        List<PasswordFindAllResponseDto> passwordResponse = new java.util.ArrayList<>(List.of());

        for (Password pass : passwords) {
            passwordResponse.add(new PasswordFindAllResponseDto(pass.getName(), pass.getPassword()));
        }

        return passwordResponse;
    }

    private String generatePassword(String title) {

        // Get the first 3 letters of the name and capitalize the first letter
        String initials = title.substring(0, 1).toUpperCase() + title.substring(1, Math.min(title.length(), 3));

        // Generate 4 random numbers
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);

        // Concatenate initials and random numbers
        String generatedString = initials + randomNumber;

        // Append or prepend random characters
        int option = random.nextInt(3); // Choose 0 for prepend, 1 for append, 2 for insert
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase character

        switch (option) {
            case 0:
                // Prepend random character
                generatedString = randomChar + generatedString;
                break;
            case 1:
                // Append random character
                generatedString += randomChar;
                break;
            case 2:
                // Choose a random position to insert
                int position = random.nextInt(generatedString.length() - 1) + 1;
                // Insert random character
                generatedString = generatedString.substring(0, position) + randomChar
                        + generatedString.substring(position);
                break;
        }

        return generatedString;
    }

}
