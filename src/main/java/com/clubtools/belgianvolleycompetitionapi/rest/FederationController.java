package com.clubtools.belgianvolleycompetitionapi.rest;

import com.clubtools.belgianvolleycompetitionapi.core.dao.TotalOverviewDao;
import com.clubtools.belgianvolleycompetitionapi.core.service.FederationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
@RestController
public class FederationController {

    @Autowired
    private FederationService service;

    @RequestMapping("/federations")
    public TotalOverviewDao getOverview() {
        return service.getOverview();
    }
}
