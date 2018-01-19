/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class CashFlowDate {

    private int month;
    private int year;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date2;


}
