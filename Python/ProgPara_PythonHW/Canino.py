# Anthony Canino
# Documentation Statement: NONE

import xml.dom.minidom as xmlhandler
import re,subprocess,os
from urllib.request import urlopen

def getLocationInfo(zipcode):
    # In order to understand the terrain of the battlefield,
    # Gurney refers to his charts, using the provided zipcode
    # to locate the target region
    locationweb = urlopen("http://where.yahooapis.com/geocode?q=" + zipcode).read()
    locationxml = xmlhandler.parseString(locationweb)

    # Gurney then cleans up the information he has gleaned from his charts
    latitude = re.sub('<.*?>','',locationxml.getElementsByTagName('latitude')[0].toxml())
    longitude = re.sub('<.*?>','',locationxml.getElementsByTagName('longitude')[0].toxml())
    cityname = re.sub('<.*?>','',locationxml.getElementsByTagName('city')[0].toxml())

    # With the geographical information gathered and prepared,
    # he returns it in a concise format
    return (latitude,longitude,cityname)

def getWeatherInfo(zipcode):
    # To understand the possible effects of weather on the battle,
    # Stilgar observes the way of the land, looking for important
    # information such as incoming windstorms or weather which
    # could conceal their approach
    weatherweb = urlopen("http://weather.yahooapis.com/forecastrss?q=" + zipcode).read()
    weatherxml = xmlhandler.parseString(weatherweb)

    # Stilgar then cleans up the information he has gathered from
    # observing the land
    curweather = re.findall('Forecast:.*?^.*?(?=<br />)',weatherxml.getElementsByTagName('item')[0].toxml(),re.S|re.M)[0]
    curweather = re.split('\n',curweather,re.M)[1]
    weatherdesc = re.findall('[a-z]+(?=\.)',curweather,re.I)[0]
    hightemp = re.findall('High:\s[0-9]+',curweather)[0]
    lowtemp = re.findall('Low:\s[0-9]+',curweather)[0]

    # With the weather information gathered and prepared,
    # he returns it in a concise format
    return (weatherdesc,hightemp,lowtemp)

def buildKML(zipcode):
    # Thufir, Muad'dibs right hand man and trusted Mentat begins
    # by collecting location and weather information

    # Gurney is entrusted with gathering geographic information
    # in order to form a good battle plan
    latitude,longitude,cityname = getLocationInfo(zipcode)

    # Stilgar is entrusted with gathering weather information
    # because he recognizes the effect of weather on a battle
    weatherdesc,hightemp,lowtemp = getWeatherInfo(zipcode)

    # With the information assembled, the Princess Irulan records
    # the information into one concise report

    # Princess Irulan begins by creating a blank placemark to hold
    # all the information for the report
    targetkml = xmlhandler.parseString("<Placemark> </Placemark>")

    # Princess Irulan starts the report with the name of the target city
    nametag = targetkml.createElement("name")
    txt = targetkml.createTextNode(cityname)
    nametag.appendChild(txt)
    targetkml.getElementsByTagName('Placemark')[0].appendChild(nametag)

    # She then adds in the current weather afflicting the target region
    desctag = targetkml.createElement("description")
    txt = targetkml.createTextNode("Current weather:\n" +
                                   weatherdesc + "\n" +
                                   hightemp + "F\n" +
                                   lowtemp + "F\n")
    desctag.appendChild(txt)
    targetkml.getElementsByTagName('Placemark')[0].appendChild(desctag)

    # Finally she records the coordinates of the location
    pointtag = targetkml.createElement("Point")
    coordtag = targetkml.createElement("coordinates")
    txt = targetkml.createTextNode(longitude + ',' + latitude + ",0")
    coordtag.appendChild(txt)
    pointtag.appendChild(coordtag)
    targetkml.getElementsByTagName('Placemark')[0].appendChild(pointtag)

    # Princess Irulan writes this to a report, then seals it for use
    # by Muad'dib
    outputfile = open('output.kml','w')
    outputfile.write(targetkml.toprettyxml())
    outputfile.close()

def weather(zipcode):
    # Our intrepid hero, Muad'dib, begins his journey to conquer a
    # new territory

    # He begins by having his trusty lieutenants build a report for
    # his viewing pleasure
    buildKML(zipcode)
    # In order to better understand the report, it is presented to
    # Muad'dib using google earth
    subprocess.Popen(['C:/Program Files (x86)/Google/Google Earth/client/googleearth.exe',os.getcwd() + '/output.kml'])
    # With the tactical information before him, Muad'dib is able
    # to continue his Jihad against the galaxy

if __name__ == "__main__":
    weather('29420')
