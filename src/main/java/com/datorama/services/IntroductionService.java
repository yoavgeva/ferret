/*
 *  Copyright (c) 2021, salesforce.com, inc.
 *  All rights reserved.
 *  SPDX-License-Identifier: BSD-3-Clause
 *  For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 *
 */

package com.datorama.services;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import com.datorama.models.Introduction;

import ch.qos.logback.classic.Logger;

public class IntroductionService {
	private static IntroductionService introductionService;
	private final Logger log = (Logger) LoggerFactory.getLogger(IntroductionService.class);
	OutputService outputService = OutputService.getInstance();
	private boolean calledOnce;

	private IntroductionService() {
		//Deny init
	}

	public static IntroductionService getInstance() {
		if (introductionService == null) {
			synchronized (IntroductionService.class) {
				if (introductionService == null) {
					introductionService = new IntroductionService();
				}
			}
		}
		return introductionService;
	}

	public void printIntroduction(Introduction introduction) {
		if(!calledOnce){
			if (ObjectUtils.isNotEmpty(introduction) && StringUtils.isNotEmpty(introduction.getMessage())) {
				outputService.normal(introduction.getMessage());
			}
			calledOnce = true;
		}
	}
}
