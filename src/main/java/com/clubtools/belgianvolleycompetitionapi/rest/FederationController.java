package com.clubtools.belgianvolleycompetitionapi.rest;

import com.clubtools.belgianvolleycompetitionapi.core.dao.FederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.TotalOverviewDao;
import com.clubtools.belgianvolleycompetitionapi.core.service.FederationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public HttpEntity<TotalOverviewDao> getOverview() {
        TotalOverviewDao overview = service.getOverview();
        return new ResponseEntity<>(overview, HttpStatus.OK);
    }

    @RequestMapping("/federations/{abbreviation}")
    public HttpEntity<FederationDao> getFederation(@PathVariable String abbreviation) {
        return null;
    }
}
