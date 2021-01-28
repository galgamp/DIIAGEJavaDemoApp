package diiage.potherat.demo.demoapp3.ui.home;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

public class HomeViewModel extends ViewModel {

    private QuoteRepository _quoteRepository;

    private MutableLiveData<String> mText;
    public MutableLiveData<Integer> numberOfQuotes;
    public MutableLiveData<Integer> numberDistinctOfSources;
    public MutableLiveData<Quote> lastQuote;


    @Inject
    @ViewModelInject
    public HomeViewModel(QuoteRepository quoteRepository) {
        mText = new MutableLiveData<>();
        numberOfQuotes = new MutableLiveData<>();
        numberDistinctOfSources = new MutableLiveData<>();
        lastQuote = new MutableLiveData<>();
        mText.setValue("This is home fragment");


        this._quoteRepository = quoteRepository;
    }

    public void loadNumberDistinctOfSources() {
        new Thread(new Runnable() {
            public void run() {
                Integer result = _quoteRepository.getCountQuote();
                numberOfQuotes.postValue(result);
            }
        }).start();
    }

    public void loadNumberOfQuotes() {
        new Thread(new Runnable() {
            public void run() {
                Integer result = _quoteRepository.getCountDistinctSource();
                numberDistinctOfSources.postValue(result);
            }
        }).start();
    }

    public void loadLastQuote() {
        new Thread(new Runnable() {
            public void run() {
                Quote result = _quoteRepository.getLast();
                lastQuote.postValue(result);
            }
        }).start();
    }
}