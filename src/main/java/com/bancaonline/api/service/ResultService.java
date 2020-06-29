package com.bancaonline.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.model.Status;
import com.bancaonline.api.repository.LotteryResultRepository;
import com.bancaonline.api.response.EntityResponse;

@Service
public class ResultService {

	@Autowired
	LotteryResultRepository resultRepository;

	/**
	 * 
	 * @param statusId
	 * @return
	 */
	public List<LotteryResult> findResultByStatus(long statusId) {

		List<LotteryResult> results = resultRepository.findByStatusId(statusId);

		return results;
	}

	/**
	 * findResultsByDrawingDate
	 * 
	 * @param drawingDate
	 * @return List<LotteryResult>
	 */
	public List<LotteryResult> findResultsByDrawingDate(String drawingDate) {

		List<LotteryResult> results = resultRepository.findByDrawingDate(drawingDate);

		return results;
	}

	/**
	 * findResultsByTypeIdAndDrawingDate
	 * 
	 * @param typeId
	 * @param drawingDate
	 * @return List<LotteryResult>
	 */
	public List<LotteryResult> findResultsByTypeIdAndDrawingDate(long typeId, String drawingDate) {

		List<LotteryResult> results = resultRepository.findByLotteryTypeIdAndDrawingDate(typeId, drawingDate);

		return results;
	}

	/**
	 * 
	 * @param results
	 * @return
	 */
	public List<EntityResponse> changeResultListToEntityResponseList(List<LotteryResult> results) {

		List<EntityResponse> responses = new ArrayList<EntityResponse>();

		results.forEach(result -> responses.add(new EntityResponse(result)));

		return responses;

	}

	/**
	 * 
	 * @param statusId
	 * @param typeId
	 * @return
	 */
	public List<LotteryResult> findResultByStatusIdAndTypeId(long statusId, long typeId) {

		List<LotteryResult> results = statusId == 0 ? resultRepository.findByLotteryTypeId(typeId)
				: resultRepository.findByLotteryTypeIdAndStatus(typeId, new Status(statusId));

		return results;
	}

}
