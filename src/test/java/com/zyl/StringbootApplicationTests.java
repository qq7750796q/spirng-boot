package com.zyl;

import com.zyl.design.domain.Taxi;
import com.zyl.design.enums.TravelScopeEnum;
import com.zyl.design.service.TaxiPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringbootApplicationTests {
	@Autowired
	private TaxiPriceService taxiPriceService;
	@Test
	public void contextLoads() {
		BigDecimal bigDecimal = new BigDecimal("5.5");
		Taxi taxi = new Taxi();

		taxi.setTravelScope(TravelScopeEnum.INNER_LOOP);

		BigDecimal bigDecimal1 = taxiPriceService.calculatePrice(new Date(), bigDecimal, taxi);

	}

	public static void main(String[] args) {

    }

}
