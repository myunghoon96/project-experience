# -*- coding: utf-8 -*-
'''
Summary

First, connect google drive.

Second, read Photo.csv using pandas to get information such as photo_id, coordiantes.

Third, matching photo_id in Photo.csv with 4851 photos in photos.zip to get 4851 coordinates.

Fourth, using meanshift with different bandwidth to get best result.

Fifth, rename all photos according to their clustered labels and analysis results.
'''

from google.colab import drive
drive.mount('/content/gdrive')

import pandas as pd
original_df = pd.read_csv('/content/gdrive/MyDrive/Photo.csv', 
                          names=['PHOTO_ID', 'USER_ID', 'X', 'Y', 'DATE', 'VALUE'])
original_df.head()

original_df.shape

import os
count=1
f = open("photoList.txt", 'w')
for file in os.listdir("/photos"):
    f.writelines([file[:-4],'\n'])
f.close()

f = open("/content/gdrive/MyDrive/photoList.txt", 'r')
photoList = f.readlines()
f.close()

small_df = pd.DataFrame(original_df, columns=['PHOTO_ID','X', 'Y'])
small_df.shape

small_df=small_df[small_df['PHOTO_ID'].isin(photoList)]

small_df.head()

df=small_df[small_df['PHOTO_ID'].isin(photoList)]
del df['PHOTO_ID']

# print(df.values)
  X=df.to_numpy()
  print(X)

# Commented out IPython magic to ensure Python compatibility.
from sklearn.cluster import MeanShift
import numpy as np
from sklearn.cluster import estimate_bandwidth

import matplotlib.pyplot as plt
# %matplotlib inline

clusterDF = pd.DataFrame(data=X, columns=['ftr1', 'ftr2'])

quantileList=[0.5]
# quantileList=[0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0]
for q in quantileList:
  # estimate_bandwidth() to get estimated best bandwidth
  best_bandwidth = estimate_bandwidth(X, quantile=q)

  meanshift= MeanShift(bandwidth=best_bandwidth)
  cluster_labels = meanshift.fit_predict(X)
  # print('cluster labels:',np.unique(cluster_labels))  
  # print('best_bandwidth ', best_bandwidth)

  clusterDF['meanshift_label']  = cluster_labels
  centers = meanshift.cluster_centers_
  unique_labels = np.unique(cluster_labels)

  for label in unique_labels:
      label_cluster = clusterDF[clusterDF['meanshift_label']==label]
      center_x_y = centers[label]
      
      plt.scatter(x=label_cluster['ftr1'], y=label_cluster['ftr2'])

      #cluster center
      plt.scatter(x=center_x_y[0], y=center_x_y[1])
      plt.scatter(x=center_x_y[0], y=center_x_y[1])
  print(q)
  temp='quantile:'+str(q)+'   bandwidth:'+str(round(best_bandwidth,3))+'    labels:'+str(len(np.unique(cluster_labels)))
  plt.xlabel(temp)
  plt.title('Geographical Clustering by meanshift')
  plt.show()

small_df['label']=cluster_labels
# small_df.head(30)

del small_df['X']
del small_df['Y']

# small_df.head(n=50)

small_df.to_csv("/content/gdrive/MyDrive/photoLabelResult.csv", sep=',')

# count=0
# photo_label=dict()
# with open('photoLabelResult.csv', 'r') as reader:

#     for line in reader:
#         if count==0:
#             count+=1
#             continue

#         fields = line.strip().split(',')
#         id=fields[1]
#         label=fields[2]
#         photo_label[id]=label
#         count += 1


# import os
# path="/photos/"
# for filename in os.listdir(path):
#     new_filename = photo_label[filename[:-4]]+'_'+filename
#     os.rename(path+filename, path+new_filename)
# print("FINISH")