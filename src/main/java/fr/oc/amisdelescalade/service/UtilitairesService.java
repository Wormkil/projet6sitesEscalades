package fr.oc.amisdelescalade.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Service
public class UtilitairesService {

    public List<Integer> getListPage(Map<String, Integer> parameters){
        var currentPage = parameters.get("currentPage");
        var elementNumber = parameters.get("elementNumber");
        var maxElementByPage = parameters.get("maxElementByPage");
        List<Integer> listPage = new ArrayList<>();
        if (elementNumber > maxElementByPage) {
            var nbPages = (elementNumber / maxElementByPage) + 1;
            if (nbPages < 11 || currentPage <= maxElementByPage) for(int i = 1; i <= nbPages; i++) listPage.add(i);
            else if (nbPages >= 11 && currentPage <= nbPages - maxElementByPage)
                for(int i = currentPage-maxElementByPage-1; i <= currentPage+maxElementByPage; i++) listPage.add(i);
            else if(currentPage > nbPages - maxElementByPage){
                var tmp = 10 - (nbPages - currentPage) - 1;
                for(int i = currentPage-tmp; i <= (currentPage-tmp)+9; i++) listPage.add(i);
            }
        }
        else listPage.add(0);
        return listPage;
    }

  /* public <A> Iterable<A> truncateIterableByParameters(Map<String, Integer> parameters, Iterable<A> iterable){
        var nbComsToTruncate = (parameters.get("currentPage")-1) * parameters.get("maxElementByPage");
        ((List<?>) iterable).stream().limit(parameters.get("maxElementByPage")).toList();
        if (parameters.get("currentPage") > 1) iterable = ((List<?>) iterable).stream().skip(nbComsToTruncate).limit(parameters.get("maxElementByPage")).toList();
        else iterable = ((List<?>) iterable).stream().limit(parameters.get("maxElementByPage")).toList();
        return iterable;
    }*/

}