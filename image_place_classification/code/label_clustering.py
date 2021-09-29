import gensim.downloader
import pandas as pd
import os
import re
glove_vectors = gensim.downloader.load('glove-twitter-200')
print("load glove_vectors finish")

#########################################
#photoDir 1: art, 3 : club, 5 : barge
photoDir = "5"
target_label = "barge"
similarity_point=0.5
# temp_count=0

stop_word=['red','work','black', 'white', 'sky', 'smile','college', 'style', 'dress','seattle', 'washington', 'march', 'woman', 'women', 'lady', 'model', 'outdoor', 'girl', 'pretty',
           'beautiful', 'people', 'person', 'pretty', 'spring', 'summer', 'us', 'me', 'lovely', 'photos', 'rm']
good_tag_list=[]
########################################




df = pd.read_csv('Tag.csv')
# print(df[:3])
df.columns=['photo_name', 'b', 'tag']
del df['b']


tag_dict=dict(df['tag'].value_counts())
for t, c in tag_dict.items():
    # 100 10 default
    if c<100 and c>10:
        good_tag_list.append(t)
        # print(t,c)

p=re.compile('[a-zA-Z]+')
p2=re.compile('[0-9]')

name_good_tag_list=[]
for i in range(len(df)):
    try:
        if p2.search(df.iloc[i][1]) != None:
            continue

        if p.match(df.iloc[i][1])==None :
            continue

        if df.iloc[i][1] in stop_word:
            continue

        if len(df.iloc[i][1])>=10 or len(df.iloc[i][1])<=1:
            continue

        if df.iloc[i][1] in good_tag_list:
            name_good_tag_list.append((df.iloc[i][0],df.iloc[i][1]))

    except:
        continue

# print(name_good_tag_list)
df_small = pd.DataFrame(name_good_tag_list, columns=['photo_name', 'tag'])
df_small['photo_name']=df_small['photo_name'].astype(str)
# print(df_small)
print("load df_samll finish")




photo_list=os.listdir(photoDir)
photo_list2=[]
for p in photo_list:
    try:
        a,b,c=p.split('_')
        photo_list2.append((a,b,c[:-4], p))
    except:
        print(p," photo list file error")

df2=pd.DataFrame(photo_list2, columns=['geo', 'visual', 'photo_name', 'full_name'])
df2['photo_name']=df2['photo_name'].astype(str)
# print(df2)
# print(df2.dtypes)
print("load photoDir df2 finish")

df3=pd.merge(df_small, df2, on='photo_name')
# print(df3)
print("df3 merge finish")

df3=df3[['photo_name', 'geo', 'visual', 'tag', 'full_name']]
# print(df3[:5])
# img_name_set=set()
visual_merge_set=set()
full_name_set = set()

for i in range(len(df3)):
    try:
        # print(df3.iloc[i][0], df3.iloc[i][3], glove_vectors.similarity(df3.iloc[i][3], target_label))
        if glove_vectors.similarity(df3.iloc[i][3], target_label)>=similarity_point:
            # img_name_set.add(df3.iloc[i][0])
            visual_merge_set.add(df3.iloc[i][2])
            # print(target_label, df3.iloc[i][3], glove_vectors.similarity(df3.iloc[i][3], target_label))
    except:
        # print("err", df3.iloc[i][0], df3.iloc[i][3])
        continue

print("visual_merge_set ", visual_merge_set)
print("len visual_merge_set ", len(visual_merge_set))

tag_dict=dict(df3['tag'].value_counts())
# print(tag_dict)
# print(df3.shape)
print("Compare Similarity FINISH")
# print(len(full_name_set))

import os
path=photoDir
for filename in os.listdir(path):
    visual_label=filename.split('_')[1]
    if visual_label=='None':
        continue
    if visual_label in visual_merge_set:
        new_filename = target_label+"_"+filename
        os.rename(path+'/'+filename, path+'/'+new_filename)

print()
print("FINISH")
