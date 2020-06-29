package com.example.demo.code.wordcount;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XieShaoping
 * @date 2020/6/24
 * @description
 */

public class Tokenizer {

    /**
     * 分词*/
    public static List<Word> segment(String sentence) {

        //1、 采用HanLP中文自然语言处理中标准分词进行分词
        List<Term> termList = HanLP.segment(sentence);
        //上面控制台打印信息就是这里输出的
        System.out.println(termList.toString());

        //2、重新封装到Word对象中（term.word代表分词后的词语，term.nature代表改词的词性）
        return termList.stream().map(term -> new Word(term.word, term.nature.toString())).collect(Collectors.toList());
    }
}
