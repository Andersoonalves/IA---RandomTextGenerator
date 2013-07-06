package model;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private String word = "";
	private int count = 1;
	private double probability = 0.0;
	private List<Word> context = new ArrayList<Word>();;

	public void addContextWord(Word word) {
		context.add(word);
	}

	public List<Word> getContext() {
		return context;
	}

	public void setContext(List<Word> context) {
		this.context = context;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	@Override
	public String toString() {
		return "Word [word=" + word + ", count=" + count + ", probability="
				+ probability + "]";
	}

}
