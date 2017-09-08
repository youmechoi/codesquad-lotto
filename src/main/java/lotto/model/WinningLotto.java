package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.model.Result.Match;
import lotto.model.InvalidException;

public class WinningLotto {
	private List<Integer> lotto;

	public WinningLotto(String lottoNo) throws InvalidException {
		String[] values = lottoNo.split(",");

		if (values.length != 6) {
			throw new InvalidException("6개의 숫자를 입력해 주세요");
		}

		List<Integer> lotto = new ArrayList<>();
		for (String value : values) {
			lotto.add(Integer.parseInt(value.trim()));
		}
		Collections.sort(lotto);
		this.lotto = lotto;

	}

	public Match countOfMatch(List<Integer> lotto) {
		List<Integer> result = new ArrayList<>(lotto);
		result.retainAll(this.lotto);

		if (result.size() < 3) {
			return null;
		}
		return Match.valueOf(result.size());
	}
}
