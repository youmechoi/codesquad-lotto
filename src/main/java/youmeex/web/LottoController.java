package youmeex.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lotto.model.InvalidException;
import lotto.model.LottoGenerator;
import lotto.model.Lottos;
import lotto.model.Result;
import lotto.model.WinningLotto;

@Controller
public class LottoController {
	private Lottos lottos;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping("/buyLotto")
	public String show(int inputMoney, String manualNumber, Model model) {
		System.out.println("Manual Number : " + manualNumber);
		lottos = LottoGenerator.generateByMoney(inputMoney);
		model.addAttribute("lottos", lottos.getLottos());
		model.addAttribute("count", lottos.count());
		return "show";
	}

	@PostMapping("/matchLotto")
	public String result(String winningNumber, Model model) {
		WinningLotto winningLotto = null;
		try {
			winningLotto = new WinningLotto(winningNumber);
		} catch (InvalidException e) {
			model.addAttribute("lottos", lottos.getLottos());
			model.addAttribute("count", lottos.count());
			model.addAttribute("errorMessage", e.getMessage());
			return "show";
		}
		Result result = lottos.match(winningLotto);
		ResultDto resultDto = ResultDto.fromResult(result);
		model.addAttribute("result", resultDto);
		return "result";
	}

}
