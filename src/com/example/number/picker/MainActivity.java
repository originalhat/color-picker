package com.example.number.picker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.NumberPicker;

public class MainActivity extends Activity {
	
  private int red   = 0;
  private int green = 0;
  private int blue  = 0;

  	@Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Begin color picking!
        startColorPicker();
    }

   // Control and execute the color picking process, start by initializing, setting defaults,
   // then listen for changes to each number picker, make updates to the color view as needed.
   private void startColorPicker() {
       // Initialize the red picker.
       NumberPicker red_picker = (NumberPicker) findViewById(R.id.red_picker);
       red_picker = initializeDefaults(red_picker);

       // Initialize the green picker.
       NumberPicker green_picker = (NumberPicker) findViewById(R.id.green_picker);
       green_picker = initializeDefaults(green_picker);

       // Initialize the red picker.
       NumberPicker blue_picker = (NumberPicker) findViewById(R.id.blue_picker);
       blue_picker = initializeDefaults(blue_picker);

       // Listen for changes, make updates if necessary.
       pickerListener(red_picker, "r");
       pickerListener(green_picker, "g");
       pickerListener(blue_picker, "b");
    }

    // Initialize a number picker with a range of 0-255, and a scroll interval of 20ms.
    private NumberPicker initializeDefaults(NumberPicker picker) {
      picker.setMaxValue(255);
      picker.setMinValue(0);
      picker.setOnLongPressUpdateInterval(20);
      return picker;
    }
   
    // Listen for changes to each 'picker', if changes are detected, update the global color,
    // then the background using RGB.
    private void pickerListener(NumberPicker picker, final String colorType) {
      
      picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {	
          updateColor(colorType, newVal);
          updateBackground();
        }
      });
    }

    // Update the global color variable, which color is updated is based on the 'colorType' 
    // which is a flag determined by the original picker listener type.
    private void updateColor(String colorType, int color) {
      if (colorType == "r")
        red = color;
      if (colorType == "g")
        green = color;
      if (colorType == "b")
        blue = color;
    }
    
    // Set the background color to the current red, green, and blue values.
    private void updateBackground() {
      View view = (View) findViewById(R.id.view); 
      view.setBackgroundColor(Color.rgb(red, green, blue));
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}