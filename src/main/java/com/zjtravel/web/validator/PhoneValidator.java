package com.zjtravel.web.validator;

import com.zjtravel.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hunger on 2017/3/6.
 */
public class PhoneValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return String.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String phone = (String) o;
        if (!ValidationUtil.isMobile(phone)) {
            errors.rejectValue("phone", null, "phone is empty.");
        }
    }
}
