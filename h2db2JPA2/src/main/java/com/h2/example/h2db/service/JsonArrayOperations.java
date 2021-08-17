package com.h2.example.h2db.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2.example.h2db.entity.LogFileEntity;
import com.h2.example.h2db.entity.ServerLogsPojo;
import com.h2.example.h2db.repository.EventJPARepository;

@Component
public class JsonArrayOperations {

	@Autowired
	EventJPARepository repository;

	ArrayList<ServerLogsPojo> lineFileJson = new ArrayList<ServerLogsPojo>();

	/* json object mapper; */
	ObjectMapper objectMapper = new ObjectMapper();

	public void createJSONArrayUsingPOJO(String line) throws JsonMappingException, JsonProcessingException {

		/* Pojo class for sever logs */
		ServerLogsPojo oneLine = new ServerLogsPojo();
		ServerLogsPojo eachLine = objectMapper.readValue(line, ServerLogsPojo.class);

		oneLine.setID(eachLine.getID());
		oneLine.setSTATE(eachLine.getSTATE());
		oneLine.setTYPE(eachLine.getTYPE());
		oneLine.setHOST(eachLine.getHOST());
		oneLine.setTIMESTAMP(eachLine.getTIMESTAMP());

		lineFileJson.add(oneLine);

		/*
		 * if u wish to writing down the whole file into json String fullFileJson =
		 * objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lineFileJson
		 * ); System.out.println(fullFileJson);
		 */

	}

	public void parseArrayList() {

		/* Sorting the Array-list by Id first & then by State */
		Collections.sort(lineFileJson, new CustomSortComparator());

		int a = 0; // for 0th index of arraylist

		for (int i = 1; i < lineFileJson.size(); i += 2) { // starting from 1st index of arraylist

			System.out.println("Running i th value is --> {}" + i);

			while (a <= lineFileJson.size() - 1) {

				if (lineFileJson.get(a).getID().equals(lineFileJson.get(i).getID())
						& (lineFileJson.get(a).getSTATE().equalsIgnoreCase("STARTED")
								| lineFileJson.get(i).getSTATE().equalsIgnoreCase("FINISHED"))
						|| (lineFileJson.get(a).getSTATE().equalsIgnoreCase("FINISHED")
								& lineFileJson.get(i).getSTATE().equalsIgnoreCase("STARTED"))) {
					// System.out.println("Match Found for Event_ID --> {}" +
					// lineFileJson.get(a).getID());
					System.out.println("Running a th value is --> {}" + a);

					/*
					 * this is in case, event duration values are coming as timestamp, but here
					 * event duration is coming in long so we can directly use it.
					 */
					Timestamp eventDuration1 = lineFileJson.get(i).getTIMESTAMP();
					long duration1 = eventDuration1.getTime();

					Timestamp eventDuration2 = lineFileJson.get(a).getTIMESTAMP();
					long duration2 = eventDuration2.getTime();
					long eventDuration;
					boolean alert;
					long snum = (long) (Math.random() * 100);

					if (duration1 > duration2) {
						eventDuration = duration1 - duration2;
						System.out.println("Event Duration -->" + eventDuration + " ms");
						alert = eventDuration >= 4 ? true : false;
						System.out.println("Alert -->" + alert);

					} else {
						eventDuration = duration2 - duration1;
						System.out.println("Event Duration -->" + eventDuration + " ms");
						alert = eventDuration >= 4 ? true : false;
						System.out.println("Alert -->" + alert);

					}

					repository.insert(new LogFileEntity(alert, eventDuration, lineFileJson.get(i).getID(),
							lineFileJson.get(i).getHOST(), lineFileJson.get(i).getTYPE(), snum));
					/*
					 * incrementing by 2 in case of match found
					 */ a += 2;

					break;

				} else {
					/*
					 * increment by 1 if match not found, 'a' will iterate till the end of
					 * Array-list for the same i th record eventid.
					 */
					a += 1;
				}
			}

		}

	}

	/* inner class for custom sort */
	static class CustomSortComparator implements Comparator<ServerLogsPojo> {
		@Override
		public int compare(ServerLogsPojo d1, ServerLogsPojo d2) {
			// for comparison
			int idCompare = d1.getID().compareTo(d2.getID());
			int stateCompare = d1.getSTATE().compareTo(d2.getSTATE());

			// 2-level comparison: comparing with ID and then with state
			return (idCompare == 0) ? stateCompare : idCompare;
		}
	}

}
