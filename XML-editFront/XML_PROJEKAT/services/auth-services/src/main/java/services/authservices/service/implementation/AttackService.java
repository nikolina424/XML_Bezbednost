package services.authservices.service.implementation;

import org.springframework.stereotype.Service;
import services.authservices.dto.ResponseDTO;
import services.authservices.service.IAttackService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AttackService implements IAttackService {

    @Override
    public ResponseDTO escaping(String input)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        if (input == null) {
            responseDTO.setBool(false);
            return responseDTO;
        }
        final String[] metaCharacters = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&","%"};

        for (int i = 0 ; i < metaCharacters.length ; i++){
            if(input.contains(metaCharacters[i])){
                input = input.replace(metaCharacters[i],'\\' + metaCharacters[i]);
            }
        }
        responseDTO.setInput(input);
        return responseDTO;
    }

    @Override
    public ResponseDTO emailValidation(String input)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        if (input == null) {
            responseDTO.setBool(false);
            return responseDTO;
        }
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        responseDTO.setBool(m.matches());
        return responseDTO;
    }

    @Override
    public ResponseDTO passwordValidation(String input)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        if (input == null) {
            responseDTO.setBool(false);
            return responseDTO;
        }

        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        responseDTO.setBool(m.matches());
        return responseDTO;
    }

    @Override
    public ResponseDTO nameValidation(String input)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        if (input == null) {
            responseDTO.setBool(false);
            return responseDTO;
        }
        String regex = "^[a-zA-Z_ \']+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        responseDTO.setBool(m.matches());
        return responseDTO;
    }

    @Override
    public ResponseDTO organisationValidation(String input)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        if (input == null) {
            responseDTO.setBool(false);
            return responseDTO;
        }

        String regex = "^[a-zA-Z0-9_ \']+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        responseDTO.setBool(m.matches());
        return responseDTO;
    }

    @Override
    public ResponseDTO phoneNumberValidation(String input)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        if (input == null) {
            responseDTO.setBool(false);
            return responseDTO;
        }

        String regex = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        responseDTO.setBool(m.matches());
        return responseDTO;
    }

    @Override
    public ResponseDTO dateValidation(String input)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        if (input == null) {
            responseDTO.setBool(false);
            return responseDTO;
        }

        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        responseDTO.setBool(m.matches());
        return responseDTO;
    }

}
