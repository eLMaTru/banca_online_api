package com.bancaonline.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.model.Status;
import com.bancaonline.api.repository.LotteryResultRepository;
import com.bancaonline.api.response.GeneralResponse;

@Service
public class LotteryResultService {
	
	@Autowired
	LotteryResultRepository lotteryResultRepository; 
	
	public GeneralResponse updateResult(long lotteryType, String drawingNumber, String drawingDate) {

		LotteryResult lastResult = lotteryResultRepository.findByLotteryTypeIdAndStatusId(lotteryType, 1L);
		LotteryResult lotteryResult = new LotteryResult();
		GeneralResponse response;

		if (lastResult.getDrawingDate().equals(drawingDate)
				&& lastResult.getWinningNumbers().length() == drawingNumber.length()) {

			lastResult.setWinningNumbers(drawingNumber);
			lotteryResult = lotteryResultRepository.save(lastResult);

			response = new GeneralResponse(true, 204, "No Content");

		} else if (!lastResult.getDrawingDate().equals(drawingDate)
				&& lastResult.getWinningNumbers().length() == drawingNumber.length()) {

			lotteryResult = lastResult;

			lastResult.setStatus(Status.Type.DISABLED.toStatus());
			lotteryResultRepository.save(lastResult);

			lotteryResult.setId(null);
			lotteryResult.setDrawingDate(drawingDate);
			lotteryResult.setCreatedDate(LocalDateTime.now());
			lotteryResult.setWinningNumbers(drawingNumber);
			lotteryResult.setStatus(Status.Type.ENABLED.toStatus());

			lotteryResultRepository.save(lotteryResult);

			response = new GeneralResponse(true, 204, "No Content");

		} else {

			response = new GeneralResponse(false, 400, "Bad Request");
		}

		return response;
	}

}
