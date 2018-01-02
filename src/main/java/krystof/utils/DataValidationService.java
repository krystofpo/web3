package krystof.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class DataValidationService {
    public List<String> correctListOfStrings(List<String> incorrectList) {


        List<String> correctList = new ArrayList<>(incorrectList);
        correctList.removeIf(e -> isBlank(e));

        return correctList;
    }
}
