package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.domain.Team;
import org.springframework.hateoas.ResourceSupport;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class MinimalTeamDao extends ResourceSupport {

    public final String name;

    public MinimalTeamDao(Team team) {
        this.name = team.getName();
    }
}
