package com.example.fanarona;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.fanarona.R;

public class MainActivity extends Activity implements OnClickListener {
	private int[] boardConfig = { 0, 1, 1, 1, 1, 2, 3, 3, 3, 3 };
	private int[] blankSpaces = new int[10];
	private Map<Integer, int[]> adjacent = new HashMap<Integer, int[]>();
	private Map<Integer, int[]> diagnol = new HashMap<Integer, int[]>();
	private final static String TAG = "MainActivity";
	private ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9;
	int playerFlag = 0;
	int MoveFlag = 0;
	int firstMove;
	int secondMove;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		i1 = (ImageView) findViewById(R.id.image1);
		i2 = (ImageView) findViewById(R.id.image2);
		i3 = (ImageView) findViewById(R.id.image3);
		i4 = (ImageView) findViewById(R.id.image4);
		i5 = (ImageView) findViewById(R.id.image5);
		i6 = (ImageView) findViewById(R.id.image6);
		i7 = (ImageView) findViewById(R.id.image7);
		i8 = (ImageView) findViewById(R.id.image8);
		i9 = (ImageView) findViewById(R.id.image9);
		i1.setOnClickListener(this);
		i2.setOnClickListener(this);
		i3.setOnClickListener(this);
		i4.setOnClickListener(this);
		i5.setOnClickListener(this);
		i6.setOnClickListener(this);
		i7.setOnClickListener(this);
		i8.setOnClickListener(this);
		i9.setOnClickListener(this);
		adjacent.put(1, new int[] { 2, 4, 5 });
		adjacent.put(2, new int[] { 1, 3, 5 });
		adjacent.put(3, new int[] { 2, 5, 6 });
		adjacent.put(4, new int[] { 1, 7, 5 });
		adjacent.put(5, new int[] { 1, 2, 3, 4, 6, 7, 8, 9 });
		adjacent.put(6, new int[] { 3, 5, 9 });
		adjacent.put(7, new int[] { 4, 5, 8 });
		adjacent.put(8, new int[] { 5, 7, 9 });
		adjacent.put(9, new int[] { 5, 6, 8 });

	}

	@Override
	protected void onResume() {
		super.onResume();
		setBlankSpaces();

	}

	public void setBlankSpaces() {
		int k = 0;
		for (int i = 0; i < boardConfig.length; i++) {
			if (boardConfig[i] == 2) {
				blankSpaces[k] = i;
				k++;
			}

		}
	}

	@Override
	public void onClick(View v) {
		// int id = v.getId();
		String resourceName = getResources().getResourceName(v.getId());
		Log.d(TAG, "onClick->resourceName->" + resourceName);
		int resourceNumber = Integer.valueOf(resourceName
				.replaceAll("\\D+", ""));
		Log.d(TAG, "onClick->resourceName->" + resourceNumber);

		if (playerFlag == 0) {
			Log.d(TAG, "onClick->playerFlag==0");
			if (MoveFlag == 0) {
				Log.d(TAG, "onClick->MoveFlag==0");
				if (boardConfig[resourceNumber] == 3) {
					Log.d(TAG, "player selected his coin");
					firstMove = resourceNumber;
					MoveFlag = 1;
				}

			} else if (MoveFlag == 1) {
				if (boardConfig[resourceNumber] == 2) {
					secondMove = resourceNumber;
					figureOutConfig();

				}
			}

		} else {
			Log.d(TAG, "figure out fucking computer move please");
		}
	}

	public void figureOutConfig() {
		Log.d(TAG, "inFigureOutConfig");
		int firstAdjFlag = 0;
		int secondAdjFlag = 0;
		int difference;
		ImageView firstImage = (ImageView) findViewById(R.id.image + firstMove);
		ImageView secondImage = (ImageView) findViewById(R.id.image
				+ secondMove);
		int third;
		int[] adjOne = adjacent.get(firstMove);
		int[] adjTwo = adjacent.get(secondMove);
		for (int i = 0; i < adjOne.length; i++) {
			if (secondMove == adjOne[i]) {
				Log.d(TAG, "inFigureOutConfig->second move is adj to first");
				firstAdjFlag = 1;
			}
		}
		if (firstAdjFlag == 1) {
			difference = firstMove - secondMove;
			for (int i = 0; i < adjTwo.length; i++) {
				if (boardConfig[adjTwo[i]] == 1) {
					ImageView thirdImage = (ImageView) findViewById(R.id.image
							+ adjTwo[i]);
					Log.d(TAG,
							"inFigureOutConfig->attack->figuring out third move");
					if (adjTwo[i] == firstMove - (2 * difference)) {
						Log.d(TAG, "inFigureOutConfig->figured out third move");

						firstImage.setImageResource(R.drawable.blue);
						secondImage.setImageResource(R.drawable.black);
						thirdImage.setImageResource(R.drawable.blue);
						secondAdjFlag = 1;
						playerFlag = 1;
					} else if (adjTwo[i] == firstMove + (2 * difference)) {
						Log.d(TAG,
								"inFigureOutConfig->atttack->figured out third move");
						firstImage.setImageResource(R.drawable.blue);
						secondImage.setImageResource(R.drawable.black);
						thirdImage.setImageResource(R.drawable.blue);
						secondAdjFlag = 1;
						playerFlag = 1;

					}
				}
			}
			if (secondAdjFlag == 0) {
				difference = firstMove - secondMove;
				for (int i = 0; i < adjOne[i]; i++) {
					if (secondMove != adjOne[i]) {
						if (boardConfig[adjOne[i]] == 1) {
							if (firstMove + difference == adjOne[i]) {
								ImageView thirdImage = (ImageView) findViewById(R.id.image
										+ adjTwo[i]);
								Log.d(TAG,
										"inFigureOutConfig->retrive->figured out third move");
								firstImage.setImageResource(R.drawable.blue);
								secondImage.setImageResource(R.drawable.black);
								thirdImage.setImageResource(R.drawable.blue);
								secondAdjFlag = 1;
								playerFlag = 1;
							}
						}
					}
				}
			}
		}

	}
}
