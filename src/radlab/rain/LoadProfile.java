/*
 * Copyright (c) 2010, Regents of the University of California
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *  * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *  * Neither the name of the University of California, Berkeley
 * nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package radlab.rain;

import org.json.JSONException;
import org.json.JSONObject;

public class LoadProfile 
{
	public static String CFG_LOAD_PROFILE_INTERVAL_KEY        = "interval";
	public static String CFG_LOAD_PROFILE_TRANSITION_TIME_KEY = "transitionTime";
	public static String CFG_LOAD_PROFILE_USERS_KEY           = "users";
	public static String CFG_LOAD_PROFILE_MIX_KEY             = "mix";

	private long   _interval;
	private long   _transitionTime;
	private int    _numberOfUsers;
	private String _mixName;

	private long _timeStarted = -1; // LoadManagerThreads need to update this every time they advance the "clock"

	public LoadProfile( JSONObject profileObj ) throws JSONException
	{
		this._interval = profileObj.getLong( CFG_LOAD_PROFILE_INTERVAL_KEY );
		this._numberOfUsers = profileObj.getInt( CFG_LOAD_PROFILE_USERS_KEY );
		this._mixName = profileObj.getString( CFG_LOAD_PROFILE_MIX_KEY );

		if ( profileObj.has( CFG_LOAD_PROFILE_TRANSITION_TIME_KEY ) )
		{
			this._transitionTime = profileObj.getLong( CFG_LOAD_PROFILE_TRANSITION_TIME_KEY );
		}
	}

	public LoadProfile( long interval, int numberOfUsers, String mixName ) throws JSONException
	{
		this(interval, numberOfUsers, mixName, 0);
	}
	
	public LoadProfile( long interval, int numberOfUsers, String mixName, long transitionTime ) throws JSONException
	{
		JSONObject profileObj = new JSONObject();
		profileObj.append( CFG_LOAD_PROFILE_INTERVAL_KEY, interval );
		profileObj.append( CFG_LOAD_PROFILE_TRANSITION_TIME_KEY, transitionTime );
		profileObj.append( CFG_LOAD_PROFILE_USERS_KEY, numberOfUsers );
		profileObj.append( CFG_LOAD_PROFILE_MIX_KEY, mixName );
	}
		
	// Converts to milliseconds
	public long getInterval() { return ( this._interval * 1000 ); }
	public void setInterval( long val ) { this._interval = val; }
	
	public int getNumberOfUsers() { return this._numberOfUsers; }
	public void setNumberOfUsers( int val ) { this._numberOfUsers = val; }
	
	public String getMixName() { return this._mixName; }
	public void setMixName( String val ) { this._mixName = val; }
	
	public long getTransitionTime() { return ( this._transitionTime * 1000 ); }
	public void setTransitionTime( long val ) { this._transitionTime = val; }
	
	public long getTimeStarted() { return this._timeStarted; }
	public void setTimeStarted( long val ) { this._timeStarted = val; }
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append( "[Duration: " + this._interval + " Users: " + this._numberOfUsers + " Mix: " + this._mixName + " Transition time: " + this._transitionTime + "]");
		return buf.toString();
	}
}