package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Word;
import aux.TxtCommonMethods;

public class Generator {
	private List<String> readTxtFile;
	private List<Word> words;
	private String fileName;

	public Generator(String fileName) throws IOException {
		this.words = new ArrayList<Word>();
		this.fileName = fileName;
		this.readTxtFile = this.getSplitedText();
	}

	public String getArchiveName() {
		return fileName;
	}

	public void setArchiveName(String archiveName) {
		this.fileName = archiveName;
	}

	private List<String> getSplitedText() throws IOException {
		return TxtCommonMethods.readTxtFile(new File(fileName));
	}

	public String generateKGrama(int k, int increment) throws IOException {
		this.getWords();
		this.setWordCount(words);
		this.addContextWords(k, increment);
		return this.generateRandomText();
	}

	private List<Word> setWordCount(List<Word> words) {
		for (int i = 0; i < words.size(); i++) {
			Word word = words.get(i);
			for (int j = i + 1; j < words.size(); j++) {
				if (word.getWord().equals(words.get(j).getWord())) {
					word.setCount(word.getCount() + 1);
					words.remove(j);
				}
			}
		}
		return words;
	}

	private void addContextWords(int k, int increment) {
		for (int i = 0; i < words.size(); i += increment) {
			Word word = words.get(i);

			for (int c = 1; c <= k; c++) {
				try {
					if ((i + c) < words.size()) {
						Word cword = words.get(i + c);
						word.addContextWord(cword);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String generateRandomText() {
		StringBuilder sb = new StringBuilder();
		int random = 0;
		for (Word word : words) {
			random = (int) (Math.random() * word.getContext().size());
			if (word.getContext().size() > 0) {
				sb.append(word.getContext().get(random).getWord() + " ");
			}
		}
		return sb.toString();
	}

	private List<Word> getWords() throws IOException {
		this.readTxtFile = this.getSplitedText();
		for (String string : this.readTxtFile) {
			Word word = new Word();
			word.setWord(string);
			this.words.add(word);
		}
		for (Word word : this.words) {
			word.setProbability((double) word.getCount() / words.size());
		}
		return words;
	}

}
