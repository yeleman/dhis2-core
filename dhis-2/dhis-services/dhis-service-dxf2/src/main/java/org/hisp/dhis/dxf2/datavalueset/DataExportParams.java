package org.hisp.dhis.dxf2.datavalueset;

/*
 * Copyright (c) 2004-2016, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.hisp.dhis.common.IdSchemes;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementGroup;
import org.hisp.dhis.dataset.DataSet;
import org.hisp.dhis.organisationunit.OrganisationUnit;
import org.hisp.dhis.period.Period;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lars Helge Overland
 */
public class DataExportParams
{
    private Set<DataSet> dataSets = new HashSet<>();
    
    private Set<DataElementGroup> dataElementGroups = new HashSet<>();

    private Set<Period> periods = new HashSet<>();

    private Date startDate;

    private Date endDate;

    private Set<OrganisationUnit> organisationUnits = new HashSet<>();

    private boolean includeChildren;
    
    private boolean includeDeleted;

    private Date lastUpdated;
    
    private String lastUpdatedDuration;

    private Integer limit;

    private IdSchemes idSchemes;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public DataExportParams()
    {
    }

    // -------------------------------------------------------------------------
    // Logic
    // -------------------------------------------------------------------------

    public Set<DataElement> getAllDataElements()
    {
        final Set<DataElement> elements = Sets.newHashSet();
        
        dataSets.forEach( ds -> elements.addAll( ds.getDataElements() ) );
        dataElementGroups.forEach( dg -> elements.addAll( dg.getMembers() ) );
        
        return ImmutableSet.copyOf( elements );
    }
    
    public DataSet getFirstDataSet()
    {
        return dataSets != null && !dataSets.isEmpty() ? dataSets.iterator().next() : null;
    }

    public Period getFirstPeriod()
    {
        return periods != null && !periods.isEmpty() ? periods.iterator().next() : null;
    }

    public boolean hasStartEndDate()
    {
        return startDate != null && endDate != null;
    }

    public OrganisationUnit getFirstOrganisationUnit()
    {
        return organisationUnits != null && !organisationUnits.isEmpty() ? organisationUnits.iterator().next() : null;
    }

    public boolean hasPeriods()
    {
        return periods != null && !periods.isEmpty();
    }
    
    public boolean hasLastUpdated()
    {
        return lastUpdated != null;
    }
    
    public boolean hasLastUpdatedDuration()
    {
        return lastUpdatedDuration != null;
    }

    public boolean hasLimit()
    {
        return limit != null;
    }

    /**
     * Indicates whether this parameters represents a single data value set, implying
     * that it contains exactly one of data sets, periods and organisation units.
     */
    public boolean isSingleDataValueSet()
    {
        return dataSets.size() == 1 && periods.size() == 1 && organisationUnits.size() == 1 && dataElementGroups.isEmpty();
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).
            add( "data sets", dataSets ).
            add( "data element groups", dataElementGroups ).
            add( "periods", periods ).
            add( "org units", organisationUnits ).
            add( "children", includeChildren ).
            add( "deleted", includeDeleted ).
            add( "id schemes", idSchemes ).toString();
    }

    // -------------------------------------------------------------------------
    // Get and set methods
    // -------------------------------------------------------------------------

    public Set<DataSet> getDataSets()
    {
        return dataSets;
    }

    public void setDataSets( Set<DataSet> dataSets )
    {
        this.dataSets = dataSets;
    }

    public Set<DataElementGroup> getDataElementGroups()
    {
        return dataElementGroups;
    }

    public void setDataElementGroups( Set<DataElementGroup> dataElementGroups )
    {
        this.dataElementGroups = dataElementGroups;
    }

    public Set<Period> getPeriods()
    {
        return periods;
    }

    public void setPeriods( Set<Period> periods )
    {
        this.periods = periods;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate( Date startDate )
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate( Date endDate )
    {
        this.endDate = endDate;
    }

    public Set<OrganisationUnit> getOrganisationUnits()
    {
        return organisationUnits;
    }

    public void setOrganisationUnits( Set<OrganisationUnit> organisationUnits )
    {
        this.organisationUnits = organisationUnits;
    }

    public boolean isIncludeChildren()
    {
        return includeChildren;
    }

    public void setIncludeChildren( boolean includeChildren )
    {
        this.includeChildren = includeChildren;
    }

    public boolean isIncludeDeleted()
    {
        return includeDeleted;
    }

    public void setIncludeDeleted( boolean includeDeleted )
    {
        this.includeDeleted = includeDeleted;
    }

    public Date getLastUpdated()
    {
        return lastUpdated;
    }

    public void setLastUpdated( Date lastUpdated )
    {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedDuration()
    {
        return lastUpdatedDuration;
    }

    public void setLastUpdatedDuration( String lastUpdatedDuration )
    {
        this.lastUpdatedDuration = lastUpdatedDuration;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public void setLimit( Integer limit )
    {
        this.limit = limit;
    }

    public IdSchemes getIdSchemes()
    {
        return idSchemes;
    }

    public void setIdSchemes( IdSchemes idSchemes )
    {
        this.idSchemes = idSchemes;
    }
}
