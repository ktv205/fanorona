package com.example.fanarona;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FiveByFive extends Activity implements OnClickListener {
	private int[] boardConfig = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 1,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
	private Map<Integer, int[]> adjacent = new HashMap<Integer, int[]>();
	private final static String TAG = "FiveByFive";
	private ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13,
			i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25;

	int playerFlag = 0;
	int MoveFlag = 0;
	int firstMove;
	int secondMove;
	int winFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fivebyfive);
		i1 = (ImageView) findViewById(R.id.images1);
		i2 = (ImageView) findViewById(R.id.images2);
		i3 = (ImageView) findViewById(R.id.images3);
		i4 = (ImageView) findViewById(R.id.images4);
		i5 = (ImageView) findViewById(R.id.images5);
		i6 = (ImageView) findViewById(R.id.images6);
		i7 = (ImageView) findViewById(R.id.images7);
		i8 = (ImageView) findViewById(R.id.images8);
		i9 = (ImageView) findViewById(R.id.images9);
		i10 = (ImageView) findViewById(R.id.images10);
		i11 = (ImageView) findViewById(R.id.images11);
		i12 = (ImageView) findViewById(R.id.images12);
		i13 = (ImageView) findViewById(R.id.images13);
		i14 = (ImageView) findViewById(R.id.images14);
		i15 = (ImageView) findViewById(R.id.images15);
		i16 = (ImageView) findViewById(R.id.images16);
		i17 = (ImageView) findViewById(R.id.images17);
		i18 = (ImageView) findViewById(R.id.images18);
		i19 = (ImageView) findViewById(R.id.images19);
		i20 = (ImageView) findViewById(R.id.images20);
		i21 = (ImageView) findViewById(R.id.images21);
		i22 = (ImageView) findViewById(R.id.images22);
		i23 = (ImageView) findViewById(R.id.images23);
		i24 = (ImageView) findViewById(R.id.images24);
		i25 = (ImageView) findViewById(R.id.images25);
		i1.setOnClickListener(this);
		i2.setOnClickListener(this);
		i3.setOnClickListener(this);
		i4.setOnClickListener(this);
		i5.setOnClickListener(this);
		i6.setOnClickListener(this);
		i7.setOnClickListener(this);
		i8.setOnClickListener(this);
		i9.setOnClickListener(this);
		i11.setOnClickListener(this);
		i12.setOnClickListener(this);
		i13.setOnClickListener(this);
		i14.setOnClickListener(this);
		i15.setOnClickListener(this);
		i16.setOnClickListener(this);
		i17.setOnClickListener(this);
		i18.setOnClickListener(this);
		i19.setOnClickListener(this);
		i21.setOnClickListener(this);
		i20.setOnClickListener(this);
		i23.setOnClickListener(this);
		i24.setOnClickListener(this);
		i25.setOnClickListener(this);
		i10.setOnClickListener(this);
		i22.setOnClickListener(this);
		adjacent.put(1, new int[] { 2, 6, 7 });
		adjacent.put(2, new int[] { 1, 3, 7 });
		adjacent.put(3, new int[] { 2, 4, 7, 8, 9 });
		adjacent.put(4, new int[] { 3, 5, 9 });
		adjacent.put(5, new int[] { 4, 9, 10 });
		adjacent.put(6, new int[] { 1, 7, 11 });
		adjacent.put(7, new int[] { 1, 2, 3, 6, 8, 11, 12, 13 });
		adjacent.put(8, new int[] { 3, 7, 9, 13 });
		adjacent.put(9, new int[] { 3, 4, 5, 8, 10, 13, 14, 15 });
		adjacent.put(10, new int[] { 5, 9, 15 });
		adjacent.put(11, new int[] { 6, 12, 16 });
		adjacent.put(12, new int[] { 6, 7, 8, 11, 12, 16, 17, 18 });
		adjacent.put(13, new int[] { 7, 8, 12, 14, 18 });
		adjacent.put(14, new int[] { 8, 9, 10, 13, 15, 18, 19, 20 });
		adjacent.put(15, new int[] { 10, 14, 20 });
		adjacent.put(16, new int[] { 11, 17, 21 });
		adjacent.put(17, new int[] { 11, 12, 13, 16, 18, 21, 22, 23 });
		adjacent.put(18, new int[] { 13, 17, 18, 23 });
		adjacent.put(19, new int[] { 13, 14, 15, 18, 20, 23, 24, 25 });
		adjacent.put(20, new int[] { 15, 19, 25 });
		adjacent.put(21, new int[] { 16, 17, 22 });
		adjacent.put(22, new int[] { 17, 21, 23 });
		adjacent.put(23, new int[] { 18, 22, 24 });
		adjacent.put(24, new int[] { 19, 23, 25 });
		adjacent.put(25, new int[] { 20, 24 });
		Button button = (Button) findViewById(R.id.fivebyfive_play);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new FanaronaAsycTask().execute();

			}
		});

	}

	@Override
	public void onClick(View v) {
		checkForWin();
		if (winFlag == 0) {
			String resourceName = getResources().getResourceName(v.getId());
			Log.d(TAG, "onClick->resourceName->" + resourceName);
			int resourceNumber = Integer.valueOf(resourceName.replaceAll(
					"\\D+", ""));
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

			}
		} else if (winFlag == 1) {
			Toast.makeText(this, "player has won", Toast.LENGTH_SHORT).show();
		} else if (winFlag == 2) {
			Toast.makeText(this, "app has won", Toast.LENGTH_SHORT).show();
		}

	}

	public void checkForWin() {
		Log.d(TAG, "check for win");
		ArrayList<Integer> black = new ArrayList<Integer>();
		ArrayList<Integer> blue = new ArrayList<Integer>();
		ArrayList<Integer> red = new ArrayList<Integer>();
		// for (int i = 0; i < boardConfig.length; i++) {
		// Log.d(TAG, "doInBackBround->boardConfig->" + i + "->"
		// + boardConfig[i]);
		// }
		for (int i = 0; i < boardConfig.length; i++) {
			if (boardConfig[i] == 1) {
				red.add(i);
			} else if (boardConfig[i] == 2) {
				blue.add(i);
			} else if (boardConfig[i] == 3) {
				black.add(i);
			}
		}
		if (red.size() == 0) {
			winFlag = 1;
		} else if (black.size() == 0) {
			winFlag = 2;
		} else {
			winFlag = 0;
		}
	}

	public void figureOutConfig() {
		Log.d(TAG, "inFigureOutConfig");
		int directionFlag = 0;
		int firstAdjFlag = 0;
		int secondAdjFlag = 0;

		int difference;
		ArrayList<Integer> thirdMove = new ArrayList<Integer>();
		Log.d(TAG, "firstMove->" + firstMove);
		Log.d(TAG, "secondMove->" + secondMove);
		int firstId = getResources().getIdentifier("images" + firstMove, "id",
				this.getPackageName());
		int secondId = getResources().getIdentifier("images" + secondMove,
				"id", this.getPackageName());
		ImageView firstImage = (ImageView) findViewById(firstId);
		ImageView secondImage = (ImageView) findViewById(secondId);
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
			int i = 0;
			int k = 2;
			while (i < adjTwo.length) {
				if (boardConfig[adjTwo[i]] == 1) {

					Log.d(TAG,
							"inFigureOutConfig->attack->figuring out third move");
					if (adjTwo[i] == firstMove - (k * difference)) {
						if (directionFlag == 0 || directionFlag == 1) {
							Log.d(TAG,
									"inFigureOutConfig->figured out third move");
							Log.d(TAG, "thirdMove->" + adjTwo[i]);
							thirdMove.add(adjTwo[i]);
							adjTwo = adjacent.get(adjTwo[i]);
							k++;
							i = -1;
							playerFlag = 1;
							directionFlag = 1;
							secondAdjFlag = 1;
							// int thirdId = getResources().getIdentifier(
							// "images" + adjTwo[i], "id",
							// this.getPackageName());
							// ImageView thirdImage = (ImageView)
							// findViewById(thirdId);
							// firstImage.setImageResource(R.drawable.blue);
							// secondImage.setImageResource(R.drawable.black);
							// thirdImage.setImageResource(R.drawable.blue);
							// boardConfig[firstMove] = 2;
							// boardConfig[secondMove] = 3;
							// boardConfig[adjTwo[i]] = 2;
							// for (int j = 0; j < boardConfig.length; j++) {
							// Log.d(TAG, "after move->boardConfig->" + j + "->"
							// + boardConfig[j]);
							// }
							// secondAdjFlag = 1;

							// Log.d(TAG, "figure out computer move please");
							// checkForWin();
							// if (winFlag == 0) {
							// new FanaronaAsycTask().execute();
							// } else if (winFlag == 1) {
							// Toast.makeText(this, "player has won",
							// Toast.LENGTH_SHORT).show();
							// } else if (winFlag == 2) {
							// Toast.makeText(this, "app has won",
							// Toast.LENGTH_SHORT).show();
							// }
							// i = adjTwo.length;
						}
					} else if (adjTwo[i] == firstMove + (k * difference)) {
						if (directionFlag == 0 || directionFlag == 2) {
							Log.d(TAG,
									"inFigureOutConfig->atttack->figured out third move");
							Log.d(TAG, "thirdMove->" + adjTwo[i]);
							thirdMove.add(adjTwo[i]);
							adjTwo = adjacent.get(adjTwo[i]);
							k++;
							playerFlag = 1;
							directionFlag = 2;
							secondAdjFlag = 1;
							// int thirdId = getResources().getIdentifier(
							// "images" + adjTwo[i], "id",
							// this.getPackageName());
							// ImageView thirdImage = (ImageView)
							// findViewById(thirdId);
							// setImages(firstMove, secondMove, thirdMove);
							// firstImage.setImageResource(R.drawable.blue);
							// secondImage.setImageResource(R.drawable.black);
							// thirdImage.setImageResource(R.drawable.blue);
							// boardConfig[firstMove] = 2;
							// boardConfig[secondMove] = 3;
							// boardConfig[adjTwo[i]] = 2;
							// for (int j = 0; j < boardConfig.length; j++) {
							// Log.d(TAG, "after move->boardConfig->" + j
							// + "->" + boardConfig[j]);
							// }
						}
						// secondAdjFlag = 1;

						// Log.d(TAG, "figure out computer move please");
						// checkForWin();
						// if (winFlag == 0) {
						// new FanaronaAsycTask().execute();
						// } else if (winFlag == 1) {
						// Toast.makeText(this, "player has won",
						// Toast.LENGTH_SHORT).show();
						// } else if (winFlag == 2) {
						// Toast.makeText(this, "app has won",
						// Toast.LENGTH_SHORT).show();
						// }
						// i = adjTwo.length;
					}
				}
				i++;
			}
			if (secondAdjFlag == 0) {
				i = 0;
				k = 1;
				difference = firstMove - secondMove;
				while (i < adjOne.length) {
					if (secondMove != adjOne[i]) {
						if (boardConfig[adjOne[i]] == 1) {
							if (firstMove + k * difference == adjOne[i]) {
								Log.d(TAG, "thirdMove->" + adjTwo[i]);
								thirdMove.add(adjTwo[i]);
								k++;
								adjTwo = adjacent.get(adjTwo[i]);
								i = -1;
								k++;
								playerFlag = 1;
								secondAdjFlag = 1;
								// int thirdId = getResources().getIdentifier(
								// "images" + adjTwo[i], "id",
								// this.getPackageName());
								// ImageView thirdImage = (ImageView)
								// findViewById(thirdId);
								// Log.d(TAG,
								// "inFigureOutConfig->retrive->figured out third move");
								// firstImage.setImageResource(R.drawable.blue);
								// secondImage.setImageResource(R.drawable.black);
								// thirdImage.setImageResource(R.drawable.blue);
								// boardConfig[firstMove] = 2;
								// boardConfig[secondMove] = 3;
								// boardConfig[adjOne[i]] = 2;
								// for (int j = 0; j < boardConfig.length; j++)
								// {
								// Log.d(TAG, "after move->boardConfig->" + j
								// + "->" + boardConfig[j]);
								// }
								//
								// secondAdjFlag = 1;
								// playerFlag = 1;
								// Log.d(TAG,
								// "figure out computer move please");
								// checkForWin();
								// if (winFlag == 0) {
								// new FanaronaAsycTask().execute();
								// } else if (winFlag == 1) {
								// Toast.makeText(this, "player has won",
								// Toast.LENGTH_SHORT).show();
								// } else if (winFlag == 2) {
								// Toast.makeText(this, "app has won",
								// Toast.LENGTH_SHORT).show();
								// }
								// i = adjOne.length;
							}
						}
					}
					i++;
				}
			}
		}
		if (firstAdjFlag == 1) {
			Log.d(TAG, "firstAdjFlag==1");
			if (secondAdjFlag == 0) {
				firstImage.setImageResource(R.drawable.blue);
				secondImage.setImageResource(R.drawable.black);
				boardConfig[firstMove] = 2;
				boardConfig[secondMove] = 3;
				playerFlag = 1;
				secondAdjFlag = 1;
				checkForWin();
				if (winFlag == 0) {
					new FanaronaAsycTask().execute();
				} else if (winFlag == 1) {
					Toast.makeText(this, "player has won", Toast.LENGTH_SHORT)
							.show();
				} else if (winFlag == 2) {
					Toast.makeText(this, "app has won", Toast.LENGTH_SHORT)
							.show();
				}

			} else if (secondAdjFlag == 1) {
				Log.d(TAG, "seconAdjFalg->" + secondAdjFlag);
				setImages(firstMove, secondMove, thirdMove);
				if (winFlag == 0) {
					new FanaronaAsycTask().execute();
				} else if (winFlag == 1) {
					Toast.makeText(this, "player has won", Toast.LENGTH_SHORT)
							.show();
				} else if (winFlag == 2) {
					Toast.makeText(this, "app has won", Toast.LENGTH_SHORT)
							.show();
				}
				new FanaronaAsycTask().execute();

			}

		}
	}

	public void setImages(int one, int two, ArrayList<Integer> three) {
		Log.d(TAG, "setImage");
		int firstId = getResources().getIdentifier("images" + one, "id",
				this.getPackageName());
		int secondId = getResources().getIdentifier("images" + two, "id",
				this.getPackageName());
		ImageView firstImage = (ImageView) findViewById(firstId);
		ImageView secondImage = (ImageView) findViewById(secondId);
		firstImage.setImageResource(R.drawable.blue);
		secondImage.setImageResource(R.drawable.black);
		boardConfig[firstMove] = 2;
		boardConfig[secondMove] = 3;
		for (int i = 0; i < three.size(); i++) {
			int thirdId = getResources().getIdentifier("images" + three.get(i),
					"id", this.getPackageName());
			ImageView thirdImage = (ImageView) findViewById(thirdId);
			thirdImage.setImageResource(R.drawable.blue);
			boardConfig[three.get(i)] = 2;

		}

	}

	public class FanaronaAsycTask extends
			AsyncTask<Void, Void, ArrayList<Integer>> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(FiveByFive.this);
			dialog.show();
		}

		@Override
		protected ArrayList<Integer> doInBackground(Void... params) {
			ArrayList<Integer> black = new ArrayList<Integer>();
			ArrayList<Integer> blue = new ArrayList<Integer>();
			ArrayList<Integer> red = new ArrayList<Integer>();
			for (int i = 0; i < boardConfig.length; i++) {
				Log.d(TAG, "doInBackBround->boardConfig->" + i + "->"
						+ boardConfig[i]);
			}
			for (int i = 0; i < boardConfig.length; i++) {
				if (boardConfig[i] == 1) {
					red.add(i);
				} else if (boardConfig[i] == 2) {
					blue.add(i);
				} else if (boardConfig[i] == 3) {
					black.add(i);
				}
			}
			int[] previousFirstAdj = null;
			int firstAdjFlag = 0;
			int firstMove = 0;
			int secondMove = 0;
			ArrayList<Integer> thirdMove = new ArrayList<Integer>();
			int redPosition = 0;
			int firstMoveFlag = 0;
			int secondMoveFlag = 0;
			int thirdMoveFlag = 0;
			int directionFlag = 0;
			int k = 2;
			while (redPosition != red.size()) {
				Log.d(TAG, "firstMove in backGround->" + red.get(redPosition));
				firstMove = red.get(redPosition);
				firstMoveFlag = 1;
				int[] firstAdj = adjacent.get(firstMove);
				previousFirstAdj = firstAdj;
				int i = 0;
				while (i < firstAdj.length) {
					if (boardConfig[firstAdj[i]] == 2) {
						Log.d(TAG, "secondMove in backGround->" + firstAdj[i]);
						secondMove = firstAdj[i];
						secondMoveFlag = 1;
						int[] secondAdj = adjacent.get(secondMove);
						int difference = firstMove - secondMove;
						Log.d(TAG, "difference between first and second Move->"
								+ difference);
						int j = 0;
						while (j < secondAdj.length) {
							if (boardConfig[secondAdj[j]] == 3) {

								Log.d(TAG, "figuring out third move->"
										+ secondAdj[j]);
								if (secondAdj[j] == firstMove
										- (k * difference)) {
									if (directionFlag == 0
											|| directionFlag == 1) {
										Log.d(TAG,
												"figured out third move-> attack->"
														+ secondAdj[j]);
										thirdMove.add(secondAdj[j]);
										thirdMoveFlag = 1;
										k++;
										directionFlag = 1;
										secondAdj = adjacent.get(secondAdj[j]);
										j = -1;

									}
								} else if (secondAdj[j] == firstMove
										+ (k * difference)) {
									if (directionFlag == 0
											|| directionFlag == 2) {
										Log.d(TAG,
												"figured out third move-> attack->"
														+ secondAdj[j]);
										thirdMoveFlag = 1;
										directionFlag = 2;
										thirdMove.add(secondAdj[j]);
										k++;
										secondAdj = adjacent.get(secondAdj[j]);
										j = -1;
									}
								}

							}
							j++;
						}
						Log.d("thirdMoveFlag", thirdMoveFlag + "");
						if (thirdMoveFlag == 0) {
							int l = 0;
							int m = 1;
							while (l < firstAdj.length) {
								if (secondMove != firstAdj[l]) {
									if (boardConfig[firstAdj[l]] == 3) {
										if (firstMove + m * difference == firstAdj[l]) {
											Log.d(TAG,
													"figured out third move-> retrive->"
															+ firstAdj[l]);
											thirdMove.add(firstAdj[l]);
											m++;
											firstAdj = adjacent
													.get(firstAdj[l]);
											thirdMoveFlag = 1;
											l = -1;
											firstAdjFlag = 1;
										}
									}
								}
								l++;
							}
						}
					}
					Log.d("size of third move", thirdMove.size() + "");
					if (secondMove == 0) {
						if (firstAdjFlag == 1) {
							Log.d("in here firstAdjFlag",
									previousFirstAdj.length + "");
							firstAdj = previousFirstAdj;
						}
						i++;

					} else if (thirdMove.size() == 0) {
						if (firstAdjFlag == 1) {
							Log.d("in here firstAdjFlag",
									previousFirstAdj.length + "");
							firstAdj = previousFirstAdj;
						}
						i++;
					} else if (thirdMove.size() != 0) {
						Log.d("in here firstAdjFlag", previousFirstAdj.length
								+ "");
						i = firstAdj.length;
					}
				}
				if (thirdMove.size() == 0) {
					redPosition++;
				} else {
					Log.d("thirdMove", thirdMove.size() + "");
					redPosition = red.size();
				}
			}

			ArrayList<Integer> finalList = new ArrayList<Integer>();
			finalList.add(firstMove);
			finalList.add(secondMove);
			for (int i = 0; i < thirdMove.size(); i++) {
				finalList.add(thirdMove.get(i));
			}

			return finalList;
			// return null;

		}

		@Override
		protected void onPostExecute(ArrayList<Integer> result) {
			dialog.dismiss();
			// Log.d(TAG,
			// "firstMove->" + result.get(0) + "secondMove->"
			// + result.get(1) + "thirdMove->" + result.get(2)
			// + "fourthMove" + result.get(3) + "fifthMove->"
			// + result.get(4));
			Log.d("size", result.size() + "");

			int firstId = getResources().getIdentifier(
					"images" + result.get(0), "id",
					FiveByFive.this.getPackageName());
			int secondId = getResources().getIdentifier(
					"images" + result.get(1), "id",
					FiveByFive.this.getPackageName());
			for (int i = 2; i < result.size(); i++) {
				int thirdId = getResources().getIdentifier(
						"images" + result.get(i), "id",
						FiveByFive.this.getPackageName());
				ImageView thirdImage = (ImageView) findViewById(thirdId);

				thirdImage.setImageResource(R.drawable.blue);
				boardConfig[result.get(i)] = 2;

			}

			ImageView firstImage = (ImageView) findViewById(firstId);
			ImageView secondImage = (ImageView) findViewById(secondId);

			firstImage.setImageResource(R.drawable.blue);
			secondImage.setImageResource(R.drawable.red);
			boardConfig[result.get(0)] = 2;
			boardConfig[result.get(1)] = 1;
			for (int i = 0; i < boardConfig.length; i++) {
				Log.d(TAG, "doInBackBround->boardConfig->" + i + "->"
						+ boardConfig[i]);
			}
			playerFlag = 0;
			MoveFlag = 0;
		}
	}
	public int alphaBeta(Node node, int depth, int alpha, int beta) {
        if (winFlag==0) {
            boolean playerWin = false;
			int winRating = 0;
			return playerWin ? winRating : -winRating;
        } else if (depth <= 0) {
            int nodeRating = 0;
			return nodeRating;
        }

        List<Node> children = generateChildren(); // generates children. also rates them and applies move to copy of field. 

        int currentPlayer = 0;
		int ai=0;
		if (currentPlayer == ai) { // ai tries to maximize the score
            for (Node child : children) {
                alpha = Math.max(alpha, alphaBeta(child, depth - 1, alpha, beta));

                if (beta <= alpha) {
                    break; // cutoff
                }
            }
            return alpha;
        } else { // enemy tries to minimize the score
            for (Node child : children) {
                beta = Math.min(beta, alphaBeta(child, depth - 1, alpha, beta));
                if (beta <= alpha) {
                    break; // cutoff
                }
            }
            return beta;
        }
    }

	private List	<Node> generateChildren() {
		// TODO Auto-generated method stub
		return null;
	}
}
