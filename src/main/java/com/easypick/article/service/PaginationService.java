package com.easypick.article.service;

import java.util.List;
import java.util.stream.IntStream;

public class PaginationService {

    public static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages){

        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0 );
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

        return IntStream.range(startNumber,endNumber).boxed().toList();
    }

    public int currentBarLength(){
        return BAR_LENGTH;
    }
}
