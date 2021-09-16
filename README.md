"NasaApod"

1. The NASA APOD API is up (working) AND the phone is connected to the internet When:
The user arrives at the APOD page for the first time today Then: The page should display the
image of Astronomy Picture of the Day along with the title and explanation, for that day
2. The user has already seen the APOD page once AND the phone is not connected to
the internet When: The user arrives at the APOD page on the same day Then: The page
should display the image of Astronomy Picture of the Day along with the title and explanation,
for that day
3. The user has not seen the APOD page today AND the phone is not connected to the
internet When: The user arrives at the APOD page Then: The page should display an error
"We are not connected to the internet, showing you the last image we have." AND The page
should display the image of Astronomy Picture of the Day along with the title and explanation,
that was last seen by the user
4. The NASA APOD API is up (working) AND the phone is connected to the internet When:
The APOD image loads fully on the screen Then: The user should be able to see the complete
image without distortion or clipping

Library used
Daggar,Viewmodel,Retrofit,Room,Glide,Coroutine
Architecture: MVVM
